package com.example.javaweblab3.dao;

import com.example.javaweblab3.entity.BorrowRecord;

import java.util.List;

public interface BorrowRecordDao {
    /**
     * @return 返回List集合所有的借书记录
     * @throws Exception 抛出异常
     */
    public List<BorrowRecord> getAllRecords() throws Exception;

    /**
     *
     * @param userId 用户id
     * @return 返回List集合当前用户的借书记录
     * @throws Exception
     */
    public List<BorrowRecord> getRecordByUserId(int userId) throws Exception;
}
