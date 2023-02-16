package com.wrs.cems.service;



import com.wrs.cems.bean.Position;

import java.util.List;

public interface IPositionService {
    List<Position> getAllPositions();
    //Position getPosById(Integer id);
    List<Position> getPosByDid(Integer did);
}
