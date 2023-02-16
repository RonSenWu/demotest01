package com.wrs.cems.service.impl;


import com.wrs.cems.bean.Nation;
import com.wrs.cems.mapper.NationMapper;
import com.wrs.cems.service.INationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NationServiceImpl implements INationService {
    @Autowired
    private NationMapper nationMapper;

    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
