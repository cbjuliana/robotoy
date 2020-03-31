void _escrever(String texto){
	_lcd.setCursor(0, 1);
	_lcd.clear();
	_lcd.print(texto);
	
	delay(4000);
	
	if (texto.length() > 16)
	for (int i = 0; i < texto.length() - 16; i++) {
		_lcd.scrollDisplayLeft();
		delay(500);
	}
	
	_lcd.clear();
}

void _andarFrente(int qtde){
	_setarVelocidadeDeslocamento();
	int i = 0;
	while (i < qtde){
		_motorEsquerdo.run(FORWARD);
		_motorDireito.run(FORWARD);
		delay(1300);
		_pararAndar();
		i = i + 1;
	}
}

void _andarFrente(){
	_andarFrente(1);
}

void _andarTras(int qtde){
	_setarVelocidadeDeslocamento();
	int i = 0;
	while (i < qtde){
		_motorEsquerdo.run(BACKWARD);
		_motorDireito.run(BACKWARD);
		delay(1300);
		_pararAndar();
		i = i + 1;
	}
}

void _andarTras(){
	_andarTras(1);
}

void _virarDireita(int qtde){
	_setarVelocidadeRotacao();
	int i = 0;
	while (i < qtde){
		_motorEsquerdo.run(FORWARD);
		_motorDireito.run(BACKWARD);
		delay(900);
		_pararAndar();
		i = i + 1;
	}
}

void _virarDireita(){
	_virarDireita(1);
}

void _virarEsquerda(int qtde){
	_setarVelocidadeRotacao();
	int i = 0;
	while (i < qtde){
		_motorDireito.run(FORWARD);
		_motorEsquerdo.run(BACKWARD);
		delay(900);
		_pararAndar();
		i = i + 1;
	}
}

void _virarEsquerda(){
	_virarEsquerda(1);
}

void _setarVelocidadeDeslocamento(){
	_pararAndar();
	_motorEsquerdo.setSpeed(105);
	_motorDireito.setSpeed(105);
}

void _setarVelocidadeRotacao(){
	_pararAndar();
	_motorEsquerdo.setSpeed(120);
	_motorDireito.setSpeed(120);
}

void _pararAndar(){
	_motorEsquerdo.run(RELEASE);
	_motorDireito.run(RELEASE);
	delay(2000);
}

void _pararGirar(){
	_pararAndar();
}

void _pararVirar(){
	_pararAndar();
}

int _calcularDistanciaObjeto(){
	int leituraDoSensor = _lerSensorDistancia();
	delay(600);
	leituraDoSensor = _lerSensorDistancia();
	return leituraDoSensor;
}

boolean _temObstaculo(){
	int distanciaObstaculo = _calcularDistanciaObjeto();
	if (distanciaObstaculo <= 15){
		return true;
	} else {
		return false;
	}
}

int _lerSensorDistancia(){
	int distanciaObstaculo = 0;
	digitalWrite(_HC_SR04_TRIGGER, LOW);
	delayMicroseconds(4);
	digitalWrite(_HC_SR04_TRIGGER, HIGH);
	delayMicroseconds(20);
	digitalWrite(_HC_SR04_TRIGGER, LOW);
	delayMicroseconds(10);
	long pulseUs = pulseIn(_HC_SR04_ECHO, HIGH);
	distanciaObstaculo = pulseUs / 59;
	delay(300);
	return distanciaObstaculo;
}

void _emitirSom(){
	tone(_BUZZER, 300, 300);
	digitalWrite(_BUZZER, HIGH);
	delay(500);
	digitalWrite(_BUZZER, LOW);
	delay(500);
}

void _virarMotorMultiusoDireita(int qtde){
	int i = 0;
	while (i < qtde){
		_anguloSensor = _anguloSensor - 60;
		_motorMultiuso.write(_anguloSensor);
		delay(2000);
		i = i + 1;
	}
}

void _virarMotorMultiusoEsquerda(int qtde){
	int i = 0;
	while (i < qtde){
		_anguloSensor = _anguloSensor + 60;
		_motorMultiuso.write(_anguloSensor);
		delay(2000);
		i = i + 1;
	}
}

void _virarMotorMultiusoDireita(){
	_virarMotorMultiusoDireita(1);
}

void _virarMotorMultiusoEsquerda(){
	_virarMotorMultiusoEsquerda(1);
}

String _identificarCor(){
  int vlVermelho = 0;
  int vlAzul = 0;
  int vlVerde = 0;
  
  digitalWrite(_s2, LOW);
  digitalWrite(_s3, LOW);  
  vlVermelho = pulseIn(_out, digitalRead(_out) == HIGH ? LOW : HIGH);
  
  digitalWrite(_s3, HIGH);
  vlAzul = pulseIn(_out, digitalRead(_out) == HIGH ? LOW : HIGH);
  
  digitalWrite(_s2, HIGH);
  vlVerde = pulseIn(_out, digitalRead(_out) == HIGH ? LOW : HIGH);
	
  if (vlVermelho <= 5 && vlAzul <= 5 && vlVerde <= 5) {
	return branco;
  } 	
	
  if (vlVermelho > 30 && vlAzul > 30 && vlVerde > 30) {
	return preto;
  } 
  
  if (vlVermelho < vlAzul && vlVermelho < vlVerde){	
	if (vlVermelho <= 7 && vlVerde <= 10 && vlAzul <= 15) {
      return amarelo;
    } else {
      return vermelho;    
    }	
  } 
  
  if (vlAzul < vlVermelho && vlAzul < vlVerde && vlAzul > 8){	
    if (vlAzul < 18) {
      return azul;
    } else {
      return verde;      
    }	
  }
  
  if (vlVerde < vlVermelho && vlVerde <= vlAzul && vlVerde > 8){
    return verde;
  }
  
  return "cor nao indentificada";  
  
  delay(2000);
}

void _girarRodaDireitaFrente(){
	_pararAndar();
	_setarVelocidadeDeslocamento();
	int i = 0;
	while (i < 1){
		_motorDireito.run(FORWARD);
		delay(730);
		_pararAndar();
		i = i + 1;
	}
}

void _girarRodaEsquerdaFrente(){
	_setarVelocidadeDeslocamento();
	int i = 0;
	while (i < 1){
		_motorEsquerdo.run(FORWARD);
		delay(730);
		_pararAndar();
		i = i + 1;
	}
}

void _girarRodaDireitaTras(){
	_setarVelocidadeDeslocamento();
	int i = 0;
	while (i < 1){
		_motorDireito.run(BACKWARD);
		delay(730);
		_pararAndar();
		i = i + 1;
	}
}

void _girarRodaEsquerdaTras(){
	_setarVelocidadeDeslocamento();
	int i = 0;
	while (i < 1){
		_motorEsquerdo.run(BACKWARD);
		delay(730);
		_pararAndar();
		i = i + 1;
	}
}
