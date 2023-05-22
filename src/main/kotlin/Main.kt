fun main() {
    val spaceBank = Bank("Space")

    val person1 = Citizen(
        "John Doe",
        1500f,
        300f,
        spaceBank,
        "Georgia"
    )
    val person2 = Entrepreneur(
        "Jane Doe",
        1000f,
        0f,
        spaceBank,
        "Uzbekistan"
    )
    val person3 = Politician(
        "John Appleseed",
        3000f,
        6000f,
        spaceBank,
        "Georgia"
    )

    person1.work()
    person3.work()
    person1.work()
    spaceBank.transferMoney(person1, person3, 700f)
    spaceBank.transferMoney(person2, person1, 15000f)
    spaceBank.transferMoney(person3, person1, 150f)
    spaceBank.printReport()
}