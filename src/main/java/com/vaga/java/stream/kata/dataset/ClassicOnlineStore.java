package com.vaga.java.stream.kata.dataset;


import com.vaga.java.stream.kata.entity.OnlineShoppingMall;

import javax.xml.bind.JAXB;
import java.io.File;

public class ClassicOnlineStore {

    protected final OnlineShoppingMall mall =
            JAXB.unmarshal(new File("target/classes/data.xml"), OnlineShoppingMall.class);

}
