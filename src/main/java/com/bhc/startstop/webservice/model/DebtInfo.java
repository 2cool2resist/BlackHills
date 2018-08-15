package com.bhc.startstop.webservice.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DebtInfo {

    private List<DebtAccount> accounts;

    public DebtInfo() {
        this.accounts = new ArrayList<DebtAccount>();
    }
    
    public DebtInfo(DebtInfo copy) {
        this.accounts = new ArrayList<DebtAccount>();

        for(DebtAccount account : copy.getAccounts())
        	accounts.add(new DebtAccount(account));
    }

    public BigDecimal getDebt(List<String> loeList, Boolean isResidential) {
    	BigDecimal total = BigDecimal.ZERO;
    	for(DebtAccount account : this.accounts)
    		if(loeList.contains(account.getLoe()))
    			total = total.add(account.calcUtilityRequired(isResidential));
        return total;
    }


    public List<DebtAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<DebtAccount> accounts) {
        if (this.accounts == null)
            this.accounts = new ArrayList<DebtAccount>();
        this.accounts.clear();
        this.accounts.addAll(accounts);
    }

}