#include <ESP8266WiFi.h>
#include "ThingSpeak.h"
#include "secrets.h"

unsigned long myChannelNumber = SECRET_CH_ID;
const char * myReadAPIKey =  SECRET_READ_APIKEY;


char ssid[] = SECRET_SSID;   // your network SSID (name)
char pass[] = SECRET_PASS;   // your network password
int keyIndex = 0;            // your network key index number (needed only for WEP)
int status = WL_IDLE_STATUS;     // the WiFi radio's status
WiFiClient  client;

void setup() {
  Serial.begin(9600);
  delay(100);
  ThingSpeak.begin(client);
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print("WiFi");
    // Serial.println(ssid);
    // Connect to WPA/WPA2 network:m
    WiFi.begin(ssid, pass);
    // wait 10 seconds for connection:
    delay(5000);
  }
  Serial.println("Conn");
}

void loop() {

  // Connect or reconnect to WiFi
  if (WiFi.status() != WL_CONNECTED) {
    // Serial.print("Attempting to connect to SSID: ");
    // Serial.println(SECRET_SSID);
    Serial.println("WiFi");
    WiFi.begin(ssid, pass);
    while (WiFi.status() != WL_CONNECTED) {
      // WiFi.begin(ssid, pass); // Connect to WPA/WPA2 network. Change this line if using open or WEP network
      // Serial.print(".");
      delay(50);
    }
  
    Serial.println("Conn");
  }
 
  String datest;
  // Wait 1 second to update the channel again
  delay(1000);
  datest = ThingSpeak.readStringField(myChannelNumber,  1, myReadAPIKey);
   // Check the status of the read operation to see if it was successful
  int statusCode = ThingSpeak.getLastReadStatus();
  if(statusCode == 200){
    Serial.println(datest);
  }
  else{
  Serial.println(statusCode); 
  }
}