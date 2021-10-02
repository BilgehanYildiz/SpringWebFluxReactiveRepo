package com.example.webflux;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table(value = "TEST")
public class TestEntity implements Persistable<Integer> {
    @Id
    @Column("ID")
    private Integer id;
    @Column("NAME")
    private String name;
    @Column("INFO")
    private String info;

    @Transient
    private boolean newEntity;

    @Override
    @Transient
    public boolean isNew() {
        return this.newEntity || id == null;
    }

    public TestEntity setAsNew() {
        this.newEntity = true;
        return this;
    }


    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
