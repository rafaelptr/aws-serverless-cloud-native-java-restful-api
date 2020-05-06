package br.com.fiap.model;

import br.com.fiap.util.RandomUtils;
import lombok.Getter;


@Getter
public class PhotoBucket {
    private String name;

    public PhotoBucket(String country, String city, String date){
        this.name = String.format("%s-%s-%s-%s",country,city,date, RandomUtils.getRandomNumberString());
    }
}