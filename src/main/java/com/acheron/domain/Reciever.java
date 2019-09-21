package com.acheron.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reciever {
	public List<String> to;
	public List<String> cc;
	public List<String> bcc;
}
