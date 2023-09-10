
# Liitmise ja otsimise rakendus

See rakendus pakub lihtsaid aritmeetilisi toiminguid RESTful API-de kaudu.

## Eeldused

- Java 17
- Maven

## Kuidas Käivitada

1. Command line-s liikuge projekti juurkataloogi.
2. Käivitage käsk:
   
   mvn spring-boot:run (Maven peab olema PATH variable hulgas)
   
VÕI

1. Käivitage vabalt valitud IDE
2. Otsige üles fail KodutooApplication, parem hiireklikk ja vajutage run

## Saadaolevad endpointid

### 1. Liida Kaks Numbrilist Väärtust

- **URL**: `/add`
- **Meetod**: `GET`
- **URL Parameetrid**:
  - `a=[täisarv]` (kohustuslik): Esimene liidetav number. Peab olema vahemikus 0 kuni 100.
  - `b=[täisarv]` (kohustuslik): Teine liidetav number. Peab olema vahemikus 0 kuni 100.
- **Edukas Vastus**:
  - **Kood**: 200
  - **Sisu**: `{ "addend1": [täisarv], "addend2": [täisarv], "sum": [täisarv] }`
- **Vea Vastus**:
  - **Kood**: 400
  - **Sisu**: String, mis kirjeldab valideerimisvea põhjust.

**Näide**: `http://localhost:8080/add?a=5&b=10`

### 2. Otsi Eelnevaid Liitmisi

- **URL**: `/search`
- **Meetod**: `GET`
- **URL Parameetrid**:
  - `value=[täisarv]` (valikuline): Väärtus, mida otsida eelnevate liitmiste hulgast. Kui esitatud, peab olema vahemikus 0 kuni 100.
  - `order=[string]` (kohustuslik): Sorteerimise järjekord. Peab olema kas `asc` (kasvav) või `desc` (kahanev).
- **Edukas Vastus**:
  - **Kood**: 200
  - **Sisu**: Nimekiri liitmistest, mis vastavad otsingukriteeriumidele.
- **Vea Vastus**:
  - **Kood**: 400
  - **Sisu**: String, mis kirjeldab valideerimisvea põhjust.

**Näide**: `http://localhost:8080/search?value=15&order=asc`

## Märkused

- Rakendus kasutab lihtsuse huvides listi salvestamiseks, seega eelnevad liitmised kaovad rakenduse taaskäivitamisel.

## Esmased kitsaskohad

- Puudub korralik error-handling. 
- Lihtsuse huvides on siin rakenduses hetkel kasutusel wildcard: ResponseEntity<?>. Rakenduse looja on teadlik, et see ei ole maailma kõige parem lahendus ning rakenduse edasiarenduses on tarvis struktuuri muuta ning tekitada wildcardi asemel näiteks ApiResponse klass, mis sisaldaks erinevaid välju (näiteks vastuse kood, vastuse tekst ja vastuse Addition) mille hulgast saab valida, mida kuvada erinevate vastuste koodide korral.
- Hetkel puudub rakendusel automaatse testimise võimekus.
