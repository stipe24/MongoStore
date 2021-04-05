package com.stipe.mstore.service;

import com.stipe.mstore.model.Size;

import java.util.List;

public interface SizeService {

    Size save(Size size);

    List<Size> getSizes();
}