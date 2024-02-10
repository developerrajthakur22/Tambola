package com.tambola.Service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import com.tambola.Entity.Tambola;

public interface TambolaService {

	public Tambola addTambola();
	
	public Page<Map<Integer, List<List<Integer>>>> getTambola(Pageable pageable);
	
	
}
