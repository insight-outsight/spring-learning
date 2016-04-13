package org.springlearning.aop.beanNameAutoProxyCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class BillServiceImpl implements BillService {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private BillDAO billDAO;
	private BillDAO2 billDAO2;

	
	public BillDAO getBillDAO() {
		return billDAO;
	}


	public void setBillDAO(BillDAO billDAO) {
		this.billDAO = billDAO;
	}


	public BillDAO2 getBillDAO2() {
		return billDAO2;
	}


	public void setBillDAO2(BillDAO2 billDAO2) {
		this.billDAO2 = billDAO2;
	}


	@Override
	public void saveBill() throws Exception {
		try {
			billDAO.insert();
			billDAO2.insert();
			
		} catch (Exception e) {
			logger.error("insert BillItem error...", e);
			throw e;
		}		
	}


}
