package com.netcracker.servisec;

import com.netcracker.errors.EmptySearchException;

import java.io.File;
import java.util.List;

public interface SearchServices<T> {

  List<T> getAll(File file) throws EmptySearchException;

}
