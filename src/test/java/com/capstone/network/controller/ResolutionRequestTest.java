package com.capstone.network.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
  
public class ResolutionRequestTest {

    @Test
    public void testGetResolution() {
         
        ResolutionRequest resolutionRequest = new ResolutionRequest();
        resolutionRequest.setResolution("Hello, this is a message.");
 
        String result = resolutionRequest.getResolution();
 
        assertEquals("Hello, this is a message.", result);
    }

    @Test
    public void testSetResolution() {
        
        ResolutionRequest resolutionRequest = new ResolutionRequest();
 
        resolutionRequest.setResolution("Another message.");
 
        assertEquals("Another message.", resolutionRequest.getResolution());
    }
}
