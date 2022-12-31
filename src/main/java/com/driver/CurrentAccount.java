package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only
    int[] freq;
    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;
        this.freq = new int[26];
        if(balance<5000) {
            throw new MyException("Insufficient Balance");
        }
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(isValid(tradeLicenseId)) return;
        else {
            if(rearrange(tradeLicenseId)) return;
            else throw new MyException("Valid License can not be generated");
        }
    }

    public boolean isValid(String tradeLicenseId) {
        freq[tradeLicenseId.charAt(0)-'A']++;
        boolean validity = true;
        for(int i=1; i<tradeLicenseId.length(); i++) {
            freq[tradeLicenseId.charAt(i)-'A']++;
            if(tradeLicenseId.charAt(i)==tradeLicenseId.charAt(i-1)) {
                validity = false;
            }
        }
        return validity;
    }

    public boolean rearrange(String tradeLicenseId) {
        int maxInd = 0;
        for(int i=0; i<26; i++) {
            if(freq[i]>(tradeLicenseId.length()+1)/2) return false;
            if(freq[maxInd]<freq[i]) {
                maxInd = i;
            }
        }
        StringBuilder newId = new StringBuilder();
        while(newId.length()<tradeLicenseId.length()) {

            for(int i=0; i<26; i++) {
                if(i!=maxInd) {
                    if(freq[i]-->0) {
                        if(freq[maxInd]-->0) newId.append((char)(maxInd+'A'));
                        newId.append((char) (i + 'A'));
                    }
                }
            }
            if(tradeLicenseId.length()-newId.length()==1&&freq[maxInd]==1) {
                newId.append((char)(maxInd+'A'));
                break;
            }
        }
        this.tradeLicenseId = newId.toString();
        return true;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }
}
