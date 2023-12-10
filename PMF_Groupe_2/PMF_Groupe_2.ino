#include "DHT.h"
DHT dht1(2, DHT22); // Capteur DHT22 à l'intérieur du réfrigérateur
DHT dht2(6, DHT22); // Capteur DHT22 à l'extérieur du réfrigérateur

// Constante mesuree avec un thermometre de reference
// qui sert a etalonner le capteur de temperature
#define DELTA_TEMPERATURE 0.7
#define brocheDeCommande      4                   // On choisit d’émettre sur la broche D3
int consigne = 17.9;
float seuilTemperature = 30.5;
float dewPoint;          // Point de rosée


void setup() {
  dht1.begin();
  dht2.begin();
  Serial.begin(9600);
  pinMode(4, OUTPUT);

}

void loop() {
  float tauxHumiditeInterieur = dht1.readHumidity();
  float temperatureInterieur = (dht1.readTemperature() - DELTA_TEMPERATURE);

  // Calcul du point de rosée
  double a = 17.27;
  double b = 237.7;
  double temp = (a * temperatureInterieur) / (b + temperatureInterieur) + log(tauxHumiditeInterieur/100.0);
  dewPoint = (b * temp) / (a - temp);


  float tauxHumiditeExterieur = dht2.readHumidity();
  float temperatureExterieur = (dht2.readTemperature()  - DELTA_TEMPERATURE);
  float seuilTemperature = (temperatureExterieur + temperatureInterieur)/2;

  // Envoi des données via le port série
  Serial.print(tauxHumiditeInterieur);
  Serial.print(",");
  Serial.print(temperatureInterieur);
  Serial.print(",");
  Serial.print(temperatureExterieur);
  Serial.print(",");
  Serial.print(dewPoint);
  Serial.println();

  
  // Pour l'activation du module peltier
  if (temperatureInterieur > consigne) {
  
    digitalWrite(4, HIGH);//Mettre 255 au lieu de HIGH et Mettre analogWrite a la place et changer le pin a 3
    Serial.println("Activation Module Peltier");
  } else {
    digitalWrite(4, LOW);// Mettre 255 au lieu de LOW et Mettre analogWrite a la place et changer le pin a 3
    Serial.println("Module Peltier Désactivé");
  }

  // Vérifier s'il y a risque de condensation
  if (temperatureInterieur <= dewPoint) {
    Serial.println("Risque de condensation !");
    // Ajoutez ici le code pour déclencher une alerte, par exemple un signal sonore ou une notification
  }

  // Vérifier s'il y a une augmentation anormale de la température
  if (temperatureInterieur > seuilTemperature) {
    Serial.println("Alerte : Temperature élevée !");
    // Ajoutez ici le code pour déclencher une alerte spécifique, par exemple un signal sonore ou une notification
  }

  // Temporisation
  delay(2000);
}