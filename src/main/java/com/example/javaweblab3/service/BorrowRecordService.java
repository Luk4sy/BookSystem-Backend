package com.example.javaweblab3.service;

import com.example.javaweblab3.dao.BorrowRecordDao;
import com.example.javaweblab3.entity.BorrowRecord;
import com.example.javaweblab3.factory.DAOFactory;

import java.util.List;

public class BorrowRecordService {
    private BorrowRecordDao borrowRecordDao;

    public BorrowRecordService() {
        this.borrowRecordDao = DAOFactory.getBorrowRecordDaoInstance();
    }

    public List<BorrowRecord> getAllRecords() throws Exception {
        return this.borrowRecordDao.getAllRecords();
    }

    public List<BorrowRecord> getRecordByUserId(int userId) throws Exception {
        return this.borrowRecordDao.getRecordByUserId(userId);
    }

    public boolean returnBook(int recordId) throws Exception {
        return this.borrowRecordDao.returnBook(recordId);
    }
}
