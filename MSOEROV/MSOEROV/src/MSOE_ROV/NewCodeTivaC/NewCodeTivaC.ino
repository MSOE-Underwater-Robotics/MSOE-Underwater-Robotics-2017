#include <Servo.h>
#include <SPI.h>         // needed for Arduino versions later than 0018
#include <Ethernet.h>
#include <EthernetUdp.h>         // UDP library from: bjoern@cs.stanford.edu 12/30/2008
#include "rovCOM.h"

const int motor_PWM[8] = {59, 51, 30, 29, 19, 75, 76, 77}; 
//                        GH  GV  Z1  Z2  Z3  Z4  RX  LX
const int motor_DIR[8] = {82, 81, 13, 12, 57, 28, 70, 69};



void setup() {

  

  for(int i=0; i<=7; i++)
  {
      pinMode(motor_PWM[i], OUTPUT);
      pinMode(motor_DIR[i], OUTPUT);
  }

  byte mac[] = {0x00, 0x1A, 0xB6, 0x03, 0x14, 0xD8};
  setupSync(mac, IPAddress(192, 168, 2, 217), 15, 4545);
  //Serial.begin(9600);
  
}

void updateMotors(){
  int * ins = (int*)(&inGroup);

  for(int ii=0;ii<=7;ii++)
  {
    int mVal = (int)*(ins+ii);
    if(mVal < 0 ){
      digitalWrite(motor_DIR[ii], HIGH);
      analogWrite(motor_PWM[ii],  -mVal);
     
    }
    else {
      digitalWrite(motor_DIR[ii],LOW);
      analogWrite(motor_PWM[ii],mVal);
      
    }

    //Serial.println(mVal);
    //delay(10);
  }
}

void loop() {

 boolean newSyncData = updateSync();
 if(newSyncData){
 updateMotors();
}
}
