class BankAccount {
    private balance

    BankAccount(openingBalance) {
        balance = openingBalance
    }

    def void deposit(amount) {
        balance += amount
    }

    def void withdraw(amount) {
        if (amount > balance) {
            throw new InsufficientFundsException()
        }
        balance -= amount
    }

    def void accureInterest() {
        def service = new InterestRateService()
        def rate = service.getInterestRate()

        // example: 50 = 500 * 0.10
        def accuredInterest = balance * rate

        deposit(accuredInterest)
    }
}