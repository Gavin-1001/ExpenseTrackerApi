export default class Expense{
    constructor (expenseId, expenseTitle, expenseDescription, expensePrice, expenseDateTime, expenseUser){
        this.expenseId = expenseId;
        this.expenseTitle = expenseTitle;
        this.expenseDescription = expenseDescription;
        this.expensePrice = expensePrice;
        this.expenseDateTime = expenseDateTime;
        this.expenseUser = expenseUser;
    }
}
