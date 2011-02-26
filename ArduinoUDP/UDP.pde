#include <Servo.h>
#include <SPI.h>         
#include <Ethernet.h>
#include <Udp.h>         
#include <LED.h>


// Enter a MAC address and IP address for your controller below.
// The IP address will be dependent on your local network:
byte mac[] = { 0xDE, 0xAD, 0xBE, 0xEF, 0xFE, 0xED };
byte ip[] = { 10,0,1,7 };
byte remoteIp[4];
unsigned int remotePort;

unsigned int localPort = 8888;      // local port to listen on

// buffers for receiving and sending data
char packetBuffer[UDP_TX_PACKET_MAX_SIZE]; //buffer to hold incoming packet,
char  ReplyBuffer[] = "acknowledged";       // a string to send back
int firstCommand = 0;
//LED led(4);
Servo myservo;

void setup() {
  // start the Ethernet and UDP:
  Ethernet.begin(mac,ip);
  Udp.begin(localPort);
  myservo.attach(9);
//  Serial.begin(9600);
  delay(2000);
//  Serial.println("Started");
  pinMode(5,OUTPUT);
  pinMode(7,OUTPUT);
}

void loop() {
  // if there's data available, read a packet
  int packetSize = Udp.available(); // note that this includes the UDP header
  if(packetSize)
  {
    packetSize = packetSize - 8;      // subtract the 8 byte header
//    Serial.print("Received packet of size ");
//    Serial.println(packetSize);

    // read the packet into packetBufffer and get the senders IP addr and port number
    Udp.readPacket(packetBuffer,UDP_TX_PACKET_MAX_SIZE, remoteIp, remotePort);
//    Serial.println("Contents:");
//    Serial.println(packetBuffer);
    firstCommand = packetBuffer[0];
    if(firstCommand == '0'){
       // ledz(packetBuffer[1]);
       digitalWrite(7,HIGH);
       delay(600);
       digitalWrite(7,LOW);
//       Serial.print(".....");
    }
    else if(firstCommand == '1'){
       // ledz(packetBuffer[1]);
       digitalWrite(5,HIGH);
       delay(600);
       digitalWrite(5,LOW);
//       Serial.println(".....");
    }
    else if(firstCommand == '2'){//Servo
      controlServo(packetBuffer[1]);
    }
//    Serial.println(packetBuffer);
  }
  delay(10);
}

void controlServo(char command){
  if(command == '0'){myservo.write(80);}
    else if(command == '1'){myservo.write(120);}
    else if(command == '2'){myservo.write(40);}
}
void ledz(char command){
  
}
