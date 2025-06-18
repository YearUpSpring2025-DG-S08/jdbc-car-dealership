package com.pluralsight.data;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LeaseContractDAO {
    private static final Logger logger = LogManager.getLogger(LeaseContractDAO.class);
    private final BasicDataSource dataSource;

    public LeaseContractDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
    
}
