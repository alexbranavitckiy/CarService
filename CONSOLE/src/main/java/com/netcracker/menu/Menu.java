package com.netcracker.menu;

import java.io.IOException;
import java.util.Scanner;

public interface Menu {

    void run(Scanner in, String parentsName) throws IOException;

    void preMessage(String parentsName);

}
