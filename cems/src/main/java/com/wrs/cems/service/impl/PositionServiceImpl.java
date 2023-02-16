package com.wrs.cems.service.impl;


import com.wrs.cems.bean.Position;
import com.wrs.cems.mapper.PositionMapper;
import com.wrs.cems.service.IPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements IPositionService {
    @Autowired
    private PositionMapper positionMapper;

    @Override
    public List<Position> getAllPositions() {
        return positionMapper.getAllPositions();
    }

    @Override
    public List<Position> getPosByDid(Integer did) {
        return positionMapper.findPosByDid(did);
    }
}
