package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import main.ETConsole;
import net.sourceforge.sizeof.SizeOf;

class BSPFace {
		private int textureid,
		effect,
		type,
		vertexindex,
		numofverts,
		meshvertindex,
		nummeshverts,
		lightmapid;
	
	private int[] lmapcorner = new int[2];
	private int[] lmapsize = new int[2];
	
	private float[] lmappos = new float[3];
	private float[][] lmapbitsets = new float[2][3];
	private float[] vnormal = new float[3];
	
	private int[] size = new int[2];
}

class BSPVertex {
	private float[] position = new float[3];
	private float[] texturerecoord = new float[2];
	private float[] lightmapcoord = new float[2];
	private float[] normal = new float[3];
	
	private char[] color = new char[4];
}

class BSPTexture {
	char[] name = new char[64];
	int flags;
	int contents;
}

class BSPBrush {
	int brushSide;
	int numofbrushsides;
	int BrushTextureid;
}

class BSPLump {
	int offset;
	int length;
}

class BSP {
	public File bspfile;
	public BSPLump[] lumps = new BSPLump[16];
	public char[] entities = new char[10000];
	public BSPVertex[] vertices = new BSPVertex[10000000];
	public BSPFace[] faces = new BSPFace[10000];
	public BSPTexture[] textures = new BSPTexture[1000];
	public BSPBrush[] brushs = new BSPBrush[10000];
}

public class BSPHandler {
	private String bspFile;
	
	public BSPHandler(String bspFile)
	{
		this.bspFile = bspFile;
	}
	
	public void loadBSP() throws IOException
	{
		ETConsole console = new ETConsole();
		BSP bsp = new BSP();
		
		bsp.bspfile = new File(bspFile);
		
		if(!bsp.bspfile.exists())
		{
			console.println(">BSPHandler< ERROR: No file named " + bspFile);
		} else {
			String date = new SimpleDateFormat("[ kk:mm:ss ]").format(new Date());
			
			char[] magic = new char[64];
			
			BufferedReader bR = new BufferedReader(new FileReader(bsp.bspfile));
			
			bR.read(magic, 0, 4);
			
			if((magic[0] != 'I')||(magic[1] != 'B')||(magic[2] != 'S')||(magic[3] != 'P'))
			{
				console.println(date + " [ BSPHandler ] ERROR: Not a valid QUAKE 3 bsp file.");
			} else {
				
				
				
				int version;
				version = bR.read();
				
				if(version != 47)
				{
					console.println(date + " [ BSPHandler ] ERROR: Unknown version of Quake 3 BSP - Version: "+version);
				} else {
					console.print(date + " [ BSPHandler ] Loading " + bspFile + "...");
					
					for(int i = 0; i <= 16; i++)
					{
						bsp.lumps[i].offset = bR.read();
						bsp.lumps[i].length = bR.read();
					}
					
					// Load entities
					bR.skip(bsp.lumps[0].offset);
					bR.read(bsp.entities, 0, bsp.lumps[0].length);
					
					
					// Load textures
					bR.skip(bsp.lumps[1].offset);
					for(int j=0; j <= bsp.lumps[1].length/SizeOf.sizeOf(bsp.textures); j++)
					{
						char[] buffer = new char[72];
						bR.read(buffer, 0, 72);
						
						for(int k =0; k <= 71; k++)
						{
//							((char) & bsp.textures[j])[k] = buffer[k];
						}
						
					}
					
				}
				
			}
			
		}
		
	}
	
	public static void main(String[] args) throws IOException
	{
		BSPHandler bspHandler = new BSPHandler("railgun.bsp");
		
		bspHandler.loadBSP();
	}
}
