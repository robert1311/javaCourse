/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author rober
 */
public class Product {
    private String produtType;
    private BigDecimal matCostPerSqFt;
    private BigDecimal labCostPersqft;

    public Product(String productType){
        this.produtType = productType;
    }
    
    public String getProdutType() {
        return produtType;
    }

    public void setProdutType(String produtType) {
        this.produtType = produtType;
    }

    public BigDecimal getMatCostPerSqFt() {
        return matCostPerSqFt;
    }

    public void setMatCostPerSqFt(BigDecimal matCostPerSqFt) {
        this.matCostPerSqFt = matCostPerSqFt;
    }

    public BigDecimal getLabCostPersqft() {
        return labCostPersqft;
    }

    public void setLabCostPersqft(BigDecimal labCostPersqft) {
        this.labCostPersqft = labCostPersqft;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.produtType);
        hash = 97 * hash + Objects.hashCode(this.matCostPerSqFt);
        hash = 97 * hash + Objects.hashCode(this.labCostPersqft);
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
        final Product other = (Product) obj;
        if (!Objects.equals(this.produtType, other.produtType)) {
            return false;
        }
        if (!Objects.equals(this.matCostPerSqFt, other.matCostPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.labCostPersqft, other.labCostPersqft)) {
            return false;
        }
        return true;
    }
    
    
}
