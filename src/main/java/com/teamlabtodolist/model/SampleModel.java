package com.teamlabtodolist.model;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teamlabtodolist.entity.Sample;

public interface SampleModel extends JpaRepository <Sample, Integer> {

}