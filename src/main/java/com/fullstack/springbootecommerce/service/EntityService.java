package com.fullstack.springbootecommerce.service;

import com.fullstack.springbootecommerce.dto.JBody;
import com.fullstack.springbootecommerce.dto.ResponseMessage;

public interface EntityService {

    public ResponseMessage addOrder(JBody body);
}
