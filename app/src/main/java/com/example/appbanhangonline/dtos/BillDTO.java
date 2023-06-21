package com.example.appbanhangonline.dtos;

import com.example.appbanhangonline.models.Bill;
import com.example.appbanhangonline.models.Customer;

public class BillDTO extends Bill {
    private Customer customer;

    public BillDTO(int billID, String billCustomerID, String createdAt, double billTotalPrice, Customer customer) {
        super(billID, billCustomerID, createdAt, billTotalPrice);
        this.customer = customer;
    }

    public BillDTO() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
