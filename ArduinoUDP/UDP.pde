/*
    UDP - CribControl
    Author: Rolf Erik Lekang
    
    Translates UDP strings to digital output to devices controlling my home.

*/

#include <Servo.h>
#include <SPI.h>         
#include <Ethernet.h>
#include <Udp.h>         
#include <LED.h>

byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
byte ip[] = { 10,0,1,7 };
byte remoteIp[4];
unsigned int remotePort; unsigned int localPort = 8888;

// buffers for receiving and sending data
char packetBuffer[UDP_TX_PACKET_MAX_SIZE]; 
char  ReplyBuffer[] = "acknowledged";
int firstCommand = 0;
Servo myservo; int lightsOff = 5; int lightsOn = 6; int blindsState = 0;


void setup() {
  Ethernet.begin(mac,ip);
  Udp.begin(localPort);
  myservo.attach(9);
  delay(2000);
  pinMode(lightsOff,OUTPUT);
  pinMode(lightsOn,OUTPUT);
}

void loop() {
  int packetSize = Udp.available();
  if(packetSize){
    packetSize = packetSize - 8;      // subtract the 8 byte header
    
    Udp.readPacket(packetBuffer,UDP_TX_PACKET_MAX_SIZE, remoteIp, remotePort);
    firstCommand = packetBuffer[0];
    
    if(firstCommand == '0'){ /*Silence is gold!*/ }
    else if(firstCommand == '1'){ controlLights(packetBuffer[1]); }
    else if(firstCommand == '2'){ controlServo(packetBuffer[1]); }
  }
  delay(10);
}
void controlLights(char command){
  if(command == '0'){ 
    digitalWrite(lightsOff,HIGH);
    delay(600);
    digitalWrite(lightsOff,LOW);
  } else if(command == '1'){
    digitalWrite(lightsOn,HIGH);
    delay(600);
    digitalWrite(lightsOn,LOW);
  }
}

void controlServo(char command){
  int delta = command - blindsState; //if delta > 0 the blinds will go up
  //Only able to run and stop the servo, have to implement a counter and find out how long it takes to raise the blinds 10%
  if(command == '0'){ myservo.write(80); } //Stop
  else if(command == '1'){ myservo.write(120); } //Clockwise
  else if(command == '2'){ myservo.write(40); } //CounterClockwise
}
