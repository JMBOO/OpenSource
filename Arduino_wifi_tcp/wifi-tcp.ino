/*
 Chat  Server

 A simple server that distributes any incoming messages to all
 connected clients.  To use telnet to  your device's IP address and type.
 You can see the client's input in the serial monitor as well.

 This example is written for a network using WPA encryption. For
 WEP or WPA, change the Wifi.begin() call accordingly.


 Circuit:
 * WiFi shield attached

 created 18 Dec 2009
 by David A. Mellis
 modified 31 May 2012
 by Tom Igoe

 */

#include <SPI.h>
#include <WiFi.h>

#include <SD.h>

char ssid[] = "iptime"; //  your network SSID (name)
//char pass[] = "secretPassword";    // your network password (use for WPA, or use as key for WEP)

int keyIndex = 0;            // your network key Index number (needed only for WEP)

int status = WL_IDLE_STATUS;
String data;
WiFiServer server(23);
char packetBuffer[1024];
boolean alreadyConnected = false; // whether or not the client was connected previously
File myFile;
void setup() {
  pinMode(11, OUTPUT); //error
  pinMode(10, OUTPUT); //server on
  pinMode(9, OUTPUT); //working
  //Initialize serial and wait for port to open:
  Serial.begin(9600);

  digitalWrite(11, HIGH);
  digitalWrite(10, LOW);
  digitalWrite(9, LOW);
  while (!Serial) {
    ; // wait for serial port to connect. Needed for Leonardo only
  }
  Serial.print("Initializing SD card...");

    if (!SD.begin(4)) {
    Serial.println("initialization failed!");
    return;
  }
  
  Serial.println("initialization done.");
  // check for the presence of the shield:
  if (WiFi.status() == WL_NO_SHIELD) {
    Serial.println("WiFi shield not present");
    // don't continue:
    while (true);
  }

  String fv = WiFi.firmwareVersion();
  if ( fv != "1.1.0" )
    Serial.println("Please upgrade the firmware");

  // attempt to connect to Wifi network:
  while ( status != WL_CONNECTED) {
    Serial.print("Attempting to connect to SSID: ");
    Serial.println(ssid);
    // Connect to WPA/WPA2 network. Change this line if using open or WEP network:
    status = WiFi.begin(ssid);
    
    // wait 10 seconds for connection:
    delay(10000);
    
  }


  // start the server:
  server.begin();
  // you're connected now, so print out the status:
  printWifiStatus();
  
 
}


void loop() {
  // wait for a new client:
  WiFiClient client = server.available();
  
  // when the client sends the first byte, say hello:
  if (client) {
    if (!alreadyConnected) {
      // clead out the input buffer:
      client.flush();
      Serial.println("We have a new client");
      
      alreadyConnected = true;
    }

    if (client.available() > 0) {
      // read the bytes incoming from the client:
      //delay(100);
      char thisChar = client.read();

      //Serial.print(thisChar);
      // echo the bytes back to the client:
      //server.write(thisChar);
      // echo the bytes to the server as well:
      
       myFile = SD.open("receive.txt", FILE_WRITE);
       if (myFile) {

      if(thisChar !='\n')
      {
       data += thisChar;        
      }if(thisChar == '\n'){
        myFile.println(data); 
        Serial.println(data);
        digitalWrite(9, HIGH);
         // close the file:     
        // Serial.println("done.");
        //Serial.println("done.");
        Serial.print("Writing to receive.txt...\n");
        data=" ";
      }
      
      client.println("done. ");
      myFile.close();
      digitalWrite(10, LOW);
      digitalWrite(9, LOW);
     } else {
    // if the file didn't open, print an error:
    Serial.println("error opening receive.txt");
    myFile.close();
  }
      }

    }
  }



void printWifiStatus() {
  // print the SSID of the network you're attached to:
  Serial.print("SSID: ");
  Serial.println(WiFi.SSID());

  // print your WiFi shield's IP address:
  IPAddress ip = WiFi.localIP();
  Serial.print("IP Address: ");
  Serial.println(ip);

  // print the received signal strength:
  long rssi = WiFi.RSSI();
  Serial.print("signal strength (RSSI):");
  Serial.print(rssi);
  Serial.println(" dBm");
  digitalWrite(10, HIGH);
  digitalWrite(11, LOW);
}


