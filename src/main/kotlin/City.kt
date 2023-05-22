open class Citizen(
    open val fullName: String,
    open val salary: Float,
    open var balance: Float,
    open val bank: Bank,
    val citizenship: String = ""
) {
    fun work() {
        balance += salary
        println(
            "\n$fullName got his salary.\n" +
                    "Salary: $salary\n" +
                    "Balance: $balance\n" +
                    "----------------------\n\n"
        )
        bank.collectMonthlyCommission(this)
    }
}

class Entrepreneur(
    override val fullName: String,
    override val salary: Float,
    override var balance: Float,
    override val bank: Bank,
    val productSpectre: String
) : Citizen(fullName, salary, balance, bank)

class Politician(
    override val fullName: String,
    override val salary: Float,
    override var balance: Float,
    override val bank: Bank,
    val party: String
) : Citizen(fullName, salary, balance, bank)

class Bank(
    private val bankName: String,
    private var collectedCommission: Float = 0f
) {
    fun transferMoney(p1: Citizen, p2: Citizen, amount: Float) {
        val commissionP = when (p1) {
            is Entrepreneur -> 0.1f
            is Politician -> 0.2f
            else -> 0.0f
        }
        val finalAmount = amount - amount * commissionP
        val commissionAmount = amount * commissionP
        if (p1.balance < amount) {
            printTransferStatus(p1, p2, amount, commissionP, commissionAmount, false)
            return
        }

        p1.balance -= amount
        this.collectedCommission += commissionAmount
        p2.balance += finalAmount
        printTransferStatus(p1, p2, amount, commissionP, commissionAmount, true)
    }

    fun collectMonthlyCommission(p: Citizen) {
        val commissionP = when (p) {
            is Entrepreneur -> 0.04f
            is Politician -> 0.06f
            else -> 0.02f
        }
        val commissionAmount = p.salary * commissionP
        p.balance -= commissionAmount
        this.collectedCommission += commissionAmount
        printCommissionCollectingStatus(p, commissionP, commissionAmount)
    }

    private fun printTransferStatus(
        p1: Citizen,
        p2: Citizen,
        amount: Float,
        commissionP: Float,
        commissionAmount: Float,
        isSuccessful: Boolean
    ) {
        println(
            "\n\nTransaction Details:\n\n" +
                    "Sender:           ${p1.fullName}\n" +
                    "${p1::class.qualifiedName}\n" +
                    "Balance:          ${p1.balance}\n" +
                    "Receiver:         ${p2.fullName}\n" +
                    "${p2::class.qualifiedName}\n" +
                    "Balance:          ${p2.balance}\n" +
                    "Transfer Amount:  $amount\n" +
                    "Commission Perc:  $commissionP\n" +
                    "Commision Amount: $commissionAmount\n" +
                    "Transfer Status:  $isSuccessful\n" +
                    "----------------------\n\n"
        )
    }

    private fun printCommissionCollectingStatus(
        p: Citizen,
        commissionP: Float,
        commissionAmount: Float
    ) {
        println(
            "\n\nCommission Details:\n\n" +
                    "Citizen:          ${p.fullName}\n" +
                    "${p::class.qualifiedName}\n" +
                    "Balance:          ${p.balance}\n" +
                    "Commission Perc:  $commissionP\n" +
                    "Commision Amount: $commissionAmount\n" +
                    "" +
                    "----------------------\n\n"
        )
    }

    fun printReport() {
        println(
            "\n\nReport Details:\n\n" +
                    "Bank:                 ${this.bankName}\n" +
                    "Commission Collected: ${this.collectedCommission}\n" +
                    "----------------------\n\n"
        )
    }
}