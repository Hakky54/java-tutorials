# Bypassing and overruling SSL configuration of libraries
This example demonstrates how to bypass the SSLConfiguration of a library which does not provide a way to inject a SSLContext. It either creates a default SSLContext or it creates a SSLContext from a property file.
It makes it especially useful if you want to either customize it to your needs and you want to configure it programmatically, however it might occur that the library maintainers don't want to open the possibility to pass custom ssl configuration or it is simply not possible because it is not maintained well or not at all anymore.

This projects demonstrates bypassing the ssl configuration of mysql-connector-j also known als the java client for mysql database. It is normally configured using a property file which contains the path to your keystore files and passwords. The driver manager has not the possibility to pass a SSLContext and setting the SSLContext as default will also not work.
This mechanism will also work with other libraries.

#### Requirements
- Java 11
- Terminal
- Docker

# Demo

#### Run docker command to start your mysql database:
```
docker run -it -p 33060:3306 --name hakky54-mysql -e MYSQL_ROOT_PASSWORD='secret' mysql
```

#### Start the java application
```
mvn exec:java
```

If you happen to run this project in your IDE, please add a breakpoint at `com.mysql.cj.protocol.ExportControlled` on line number `617` You will see that a custom SSLContext is being used which is constructed by this project itself. See also the screenshot below.

![alt text](https://github.com/Hakky54/java-tutorials/blob/main/bypassing-overruling-ssl-configuration/images/demo.png?raw=true)

#### Output

Analyse the logs and you will notice that a custom SSLContextSpi. The driver manager cannot take a custom SSLContext as parameter.
It can only be configured with a property file which will be picked up by the underlying library to construct a SSLContext by itself.
The example mechanism which is provided in this project demonstrates it can bypass that configuration which makes it possible to configure your own SSLContext with your custom needs and enforce it to libraries to use that one, such as using hot reloading or other customization.
Next to that it makes it also possible to configure your ssl configuration programmatically instead of using property files.

```text
[main] DEBUG nl.altindag.ssl.SSLFactory - UnsafeTrustManager is being used. Client/Server certificates will be accepted without validation.
[main] DEBUG nl.altindag.ssl.sslcontext.FenixSSLContextSpi - The provided parameters are being ignored as the SSLContext has already been initialized
[main] DEBUG nl.altindag.ssl.trustmanager.LoggingX509ExtendedTrustManager - Validating the certificate chain of the server[localhost:33060] with authentication type UNKNOWN, while also using the Socket. See below for the full chain of the server:
[[
[
  Version: V3
  Subject: CN=MySQL_Server_8.3.0_Auto_Generated_Server_Certificate
  Signature Algorithm: SHA256withRSA, OID = 1.2.840.113549.1.1.11

  Key:  Sun RSA public key, 2048 bits
  params: null
  modulus: 26811237469526925013217732778347645028372096762757517094873881384326794143520473881931517216192978667286614137834351744930743569420331005675743465842988396222411822328614174823599080289194800138231880849889445793316237871277729916541651296079419263823200624286799125145065081745383932265360430830600074814598200653035780125993561030204188098733278288291855832039424052754336479552576186105070754785252736892832158117499013469382977407024221724338008988129640095078976439349307315029914020211738384232470237984568193374918982987251444419935528394284743049764048479296406986713874283401058450172209984979299040989572607
  public exponent: 65537
  Validity: [From: Fri Feb 23 16:13:08 CET 2024,
               To: Mon Feb 20 16:13:08 CET 2034]
  Issuer: CN=MySQL_Server_8.3.0_Auto_Generated_CA_Certificate
  SerialNumber: [    02]

Certificate Extensions: 1
[1]: ObjectId: 2.5.29.19 Criticality=true
BasicConstraints:[
  CA:false
  PathLen: undefined
]

]
  Algorithm: [SHA256withRSA]
  Signature:
0000: 30 D4 01 57 B8 4F 46 8C   73 40 A4 C0 0E 80 6F 0B  0..W.OF.s@....o.
0010: DA C2 45 4D 4F F4 5B AD   B6 E0 E2 08 B4 F4 9C 16  ..EMO.[.........
0020: 3F 06 AE 6D CE E9 89 9A   DD 36 FA 4A 16 B4 19 8D  ?..m.....6.J....
0030: 6E BA 94 0E 69 55 02 C6   26 EF DA C7 9E 0E AB F1  n...iU..&.......
0040: B2 D4 1E 51 EA 00 42 D2   A9 8A 6E 58 03 AB 66 92  ...Q..B...nX..f.
0050: 28 5C 0D 73 F5 7D 88 63   05 09 16 79 60 D6 1A CB  (\.s...c...y`...
0060: 2E 69 9E 41 A4 3E 10 C2   0D 4B E4 E4 7A E9 90 4E  .i.A.>...K..z..N
0070: C1 C3 6A 4C 2D 3E 9B 7E   8C CB 14 17 9E 06 0E D5  ..jL->..........
0080: A9 23 24 01 0C AA 01 9B   FF 37 5F 4C 15 16 8B 7C  .#$......7_L....
0090: B8 80 57 41 67 99 83 63   CE F0 88 31 99 BB 33 CB  ..WAg..c...1..3.
00A0: 61 74 E6 5A 51 60 BF B6   4B 78 63 5B F5 B6 52 4F  at.ZQ`..Kxc[..RO
00B0: 6F 43 63 81 85 93 41 71   DD EC C9 F0 B6 C4 EA 5E  oCc...Aq.......^
00C0: A8 4F 2E 35 C0 17 32 09   07 EC F8 9C 80 DD 90 72  .O.5..2........r
00D0: 06 47 41 EC BA E6 BE 6D   16 73 C1 D9 5E 87 AB B6  .GA....m.s..^...
00E0: AE F8 2F 2B 32 04 84 F1   30 8E 29 C2 43 BE C3 70  ../+2...0.).C..p
00F0: FD 19 6D F7 25 48 CD DB   15 D2 21 04 F7 42 39 C8  ..m.%H....!..B9.

], [
[
  Version: V3
  Subject: CN=MySQL_Server_8.3.0_Auto_Generated_CA_Certificate
  Signature Algorithm: SHA256withRSA, OID = 1.2.840.113549.1.1.11

  Key:  Sun RSA public key, 2048 bits
  params: null
  modulus: 18415512143394512742945158700496372181813243748686902507319873438449728269047865750428624360232093407555585273121509797138398434221593350771069575083752834302514706045970292342342373579317177963556573035180155888711316326952804058115156681476254343217858592768818948021273167844547485227730130695633468536588534013062547258888192580947411609345265775272424387666546636613109338525094294118329566317890094186017317953261098241453170033774763065838059745149699263910145380353699695944703010472191884899121093261187781997724421801851277422096710070415379194189490839309925264973682848834580699826232353355095584317375037
  public exponent: 65537
  Validity: [From: Fri Feb 23 16:13:08 CET 2024,
               To: Mon Feb 20 16:13:08 CET 2034]
  Issuer: CN=MySQL_Server_8.3.0_Auto_Generated_CA_Certificate
  SerialNumber: [    01]

Certificate Extensions: 1
[1]: ObjectId: 2.5.29.19 Criticality=true
BasicConstraints:[
  CA:true
  PathLen:2147483647
]

]
  Algorithm: [SHA256withRSA]
  Signature:
0000: 04 7D 75 EE 54 93 12 8F   69 ED BE 04 14 5B 85 14  ..u.T...i....[..
0010: 90 E9 AC 23 0A 4F 11 DB   61 D9 BF 56 98 75 46 93  ...#.O..a..V.uF.
0020: 71 C8 BD 2B 2E 29 40 BF   95 82 DC D7 37 34 E0 0E  q..+.)@.....74..
0030: 93 65 A0 B1 07 2B B1 51   F8 31 C4 1E C3 BC DA E3  .e...+.Q.1......
0040: F0 73 B0 82 E6 5C C7 B4   21 AB B1 83 9B E7 58 1A  .s...\..!.....X.
0050: 87 71 27 CC B9 C6 EA 92   AF 77 B5 F0 01 84 12 B6  .q'......w......
0060: EC 5D 0A F9 6C 4A E1 5F   A0 00 A2 AE A6 CD 12 16  .]..lJ._........
0070: 53 E1 0E 81 69 3C DE 1F   75 FD A5 CA 6D 31 BD 02  S...i<..u...m1..
0080: C0 06 1C 1B 54 61 79 BA   E5 F3 D4 C2 A1 CC 90 49  ....Tay........I
0090: 40 8A 98 9C EE 4C 4C B9   35 83 3E 70 60 FA A7 72  @....LL.5.>p`..r
00A0: 97 92 22 B3 5A CB 18 77   F7 EA ED 5F 7F E1 A8 7D  ..".Z..w..._....
00B0: 3B 17 B1 E3 1F 34 7E 71   C5 E6 05 18 DF 14 54 CB  ;....4.q......T.
00C0: 0C 9B F9 97 11 DF 6C F7   51 97 5D 7F 50 BA 2A EA  ......l.Q.].P.*.
00D0: 7D E3 70 23 B1 4E 8D 11   85 D6 1A B0 8D 01 38 66  ..p#.N........8f
00E0: BA 15 67 06 04 D6 3A 35   6C 85 B5 7B AC E7 90 B5  ..g...:5l.......
00F0: CF 0E BB 50 B9 30 2E 24   35 64 13 A4 50 01 C1 7F  ...P.0.$5d..P...

]]
Database connected!
```
