/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.dto;

import java.util.Objects;

/**
 *
 * @author rober
 */
public class Reservoir {
    
    private String reservoirType;
    private int pennies;
    private int nickels;
    private int dimes;
    private int quarters;

    public Reservoir(){
        
    }
    
    public Reservoir(String reservoirType){
        this.reservoirType = reservoirType;
    }
    
    public String getReservoirType() {
        return reservoirType;
    }

    public int getPennies() {
        return pennies;
    }

    public void setPennies(int pennies) {
        this.pennies = pennies;
    }

    public int getNickels() {
        return nickels;
    }

    public void setNickels(int nickels) {
        this.nickels = nickels;
    }

    public int getDimes() {
        return dimes;
    }

    public void setDimes(int dimes) {
        this.dimes = dimes;
    }

    public int getQuarters() {
        return quarters;
    }

    public void setQuarters(int quarters) {
        this.quarters = quarters;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.reservoirType);
        hash = 89 * hash + this.pennies;
        hash = 89 * hash + this.nickels;
        hash = 89 * hash + this.dimes;
        hash = 89 * hash + this.quarters;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservoir other = (Reservoir) obj;
        if (this.pennies != other.pennies) {
            return false;
        }
        if (this.nickels != other.nickels) {
            return false;
        }
        if (this.dimes != other.dimes) {
            return false;
        }
        if (this.quarters != other.quarters) {
            return false;
        }
        if (!Objects.equals(this.reservoirType, other.reservoirType)) {
            return false;
        }
        return true;
    }
    

    
}
