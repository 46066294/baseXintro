/*
 * Programa: BX_Client01.java
 * Objectiu: Programa que mostri els noms dels continents emmagatzemats en
 *           el document "mondial.xml" de la base de dades "mondial"
 * Autor...: Isidre Guix�
 */

import org.basex.api.client.ClientSession;

import java.io.IOException;

public final class BX_Client01 {
  // Amaguem el constructor per defecte. */
  private BX_Client01() { }

  public static void main(String[] args) {
    ClientSession session = null;
    try {
      // Obrir sessi�:
      session = new ClientSession("localhost", 1984, "admin", "admin");//localhost      172.31.83.197      8984
      // Aquest client de BaseX no facilita funcions per indicar en quina
      // BD treballar... Per tant, ens caldr� utilitzar la funci� collection()
      // per recollir els documents de la base de dades que ens interessa
      // utilitzar i efectuar les consultes a partir d'ells.
      // A m�s, �s adequat indicar el document sobre el qual consultar, per si
      // la base de dades cont� m�s documents.
      // Preparem la instrucci� a executar
      //    for $doc in collection('mondial')
      //    where document-uri($doc)='mondial.xml'
      //    return $doc//continent/@name
      //String cad = "for $doc in collection('mondial.xml') ";
      //cad = cad + "where document-uri($doc) = \"mondial.xml\" ";
      //cad = cad + "return $doc//continent/@name/string()";
      String cad = "collection('mondial.xml')//name/string()";
      //String cad = "collection('mondial.xml')//..";
      // Executem la consulta
      System.out.println("Executant consulta: "+cad);  
      System.out.println(session.query(cad).execute());
    }
    catch (IOException ioe) {
      ioe.printStackTrace();
    }
    finally { /* Tanquem sessi� en qualsevol cas */
      try {
          if(session != null) session.close();
      }
      catch(IOException ioe) {
          ioe.printStackTrace();
      }
    }
  }
}
// IOC:
// http://ioc.xtec.cat/materials/FP/Materials/2252_DAM/DAM_2252_M06/web/html/WebContent/u3/a1/activitats.html