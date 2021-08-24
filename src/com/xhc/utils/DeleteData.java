package com.xhc.utils;

import com.xhc.mapper.IMapper;
import org.apache.ibatis.session.SqlSession;

public class DeleteData {
    /**
     * 删除household表
     * @return
     */
    public static boolean deleteHouseHold(){
        SqlSession session = Initial.getSession();
        IMapper mapper = session.getMapper(IMapper.class);
        boolean houseHold = mapper.deleteHouseHold();
        session.commit();
        session.close();
        return houseHold;
    }

    /**
     * 删除family表
     * @return
     */
    public static boolean deleteFamily(){
        SqlSession session = Initial.getSession();
        IMapper mapper = session.getMapper(IMapper.class);
        boolean result = mapper.deleteFamily();
        session.commit();
        session.close();
        return result;
    }

    /**
     * 删除state表
     * @return
     */
    public static boolean deleteState(){
        SqlSession session = Initial.getSession();
        IMapper mapper = session.getMapper(IMapper.class);
        boolean result = mapper.deleteState();
        session.commit();
        session.close();
        return result;
    }

    /**
     * 删除picture
     * @return
     */
    public static boolean deletePicture(){
        SqlSession session = Initial.getSession();
        IMapper mapper = session.getMapper(IMapper.class);
        boolean result = mapper.deletePicture();
        session.commit();
        session.close();
        return result;
    }
}
