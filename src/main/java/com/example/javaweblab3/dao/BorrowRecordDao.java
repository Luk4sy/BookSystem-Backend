package com.example.javaweblab3.dao;

import com.example.javaweblab3.entity.BorrowRecord;

import java.util.Date;
import java.util.List;

public interface BorrowRecordDao {
    /**
     * @return 返回List集合所有的借书记录
     * @throws Exception 抛出异常
     */
    public List<BorrowRecord> getAllRecords() throws Exception;

    /**
     * @param userId 用户id
     * @return 返回List集合当前用户的借书记录
     * @throws Exception 抛出异常
     */
    public List<BorrowRecord> getRecordByUserId(int userId) throws Exception;

    /**
     * @param recordId 借书记录id
     * @return 返回boolean判断是否更新成功
     * @throws Exception 抛出异常
     */
    public boolean returnBook(int recordId) throws Exception;

    /**
     * @param userId 用户id
     * @param bookId 书籍id
     * @param date   归还日期
     * @throws Exception 抛出异常
     */
    public void addRecord(int userId, int bookId, Date date) throws Exception;
}
