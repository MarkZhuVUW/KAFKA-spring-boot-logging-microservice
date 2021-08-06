package net.markz;

import net.markz.services.event.EventServiceImpl;
import net.markz.services.fileread.FileReadingServiceImpl;

public class Main {

  public static void main(String[] args) {

    var resource = new Resource(new EventServiceImpl(), new FileReadingServiceImpl());

    // Force JIT optimizations to make the performance measurements for calculating the ETAs faster
    // and more accurate. Obviously in production environment we will probably be constantly running
    // the program and calculate heaps more ETAs. This method will not be needed in production.
    resource.nukeTheJIT();

    // Run the calculations with optimal speed.
    resource.calculateAllETAs();
  }
}
