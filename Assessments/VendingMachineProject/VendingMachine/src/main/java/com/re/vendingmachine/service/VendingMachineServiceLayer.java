/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.vendingmachine.service;

import com.re.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author rober
 */
public interface VendingMachineServiceLayer {
    
    List<Item> getFullItemList();
}
