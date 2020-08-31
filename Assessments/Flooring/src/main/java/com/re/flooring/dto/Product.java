/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.flooring.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author rober
 */
public class Product {
    private String productType;
    private BigDecimal matCostPerSqFt;
    private BigDecimal labCostPersqft;

    public Product(String productType){
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getMatCostPerSqFt() {
        return matCostPerSqFt;
    }

    public void setMatCostPerSqFt(BigDecimal matCostPerSqFt) {
        this.matCostPerSqFt = matCostPerSqFt.setScale(2, RoundingMode.HALF_UP);;
    }

    public BigDecimal getLabCostPersqft() {
        return labCostPersqft;
    }

    public void setLabCostPersqft(BigDecimal labCostPersqft) {
        this.labCostPersqft = labCostPersqft.setScale(2, RoundingMode.HALF_UP);;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.productType);
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
        if (!Objects.equals(this.productType, other.productType)) {
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
