import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;



public class Principal {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(args));
		System.out.println("Loading File");
		if (args.length==0)
			System.exit(1);
		
		for (String file : args) {
			String arString=file;
			List<DocumentsV> Documentos=Load(arString);
			System.out.println("File Loaded");
			
			write(Documentos,arString);
		
			System.out.println("Simulation End time");
		}

	}

	private static void write(List<DocumentsV> documentos, String arString) {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
        	File F=new File(arString);
        	String FileName=F.getName().substring(0, F.getName().length()-4);
            fichero = new FileWriter(FileName+"_new.dot");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < documentos.size(); i++)
            	{
            	DocumentsV actual=documentos.get(i);
            	if (!actual.getAtt().isEmpty())
            		{
            		for (Long valor : actual.getAtt())
            			pw.print(valor+" ");
            		pw.println();
            		}
                
                }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		
	}

	/**
	 * Clase que carga el archivo
	 * @param arString
	 * @return
	 */
	protected static List<DocumentsV> Load(String pathname) {
		List<DocumentsV> Salida=new ArrayList<DocumentsV>();
		HashMap<Long,Long> Actuales=new HashMap<Long,Long>();
		FileReader fr = null;
		BufferedReader br = null;
		Long idcounter=1l;
		
		try {
			File F=new File(pathname);
			fr = new FileReader(F);
			br = new BufferedReader(fr);
			String linea;
	         while((linea=br.readLine())!=null)
	            {
	        	 DocumentsV DV=new DocumentsV(idcounter.longValue());
	        	 idcounter++;
	        		 System.out.println(linea);
	             String[] lineaNE=linea.split(" ");
	             for (String stringatt : lineaNE) {
	            	try {
						Long attr=Long.parseLong(stringatt);
						if (attr!=0)
						{
						Long attr2 = Actuales.get(attr);

						if (attr2==null)
							Actuales.put(attr, attr);
						else
							attr=attr2;
						DV.getAtt().add(attr);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} 
	             }
	             if (!Salida.contains(DV))
	            	 Salida.add(DV);
	            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try{                    
	            if( fr != null ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
		}
		
		return Salida;
	}
	
}
