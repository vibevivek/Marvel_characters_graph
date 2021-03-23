import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
public class A4_2019CS10413{
	public void average(String path1, String path2){
		String line1="";
		String line2="";
		int no_nodes=-1;
		int no_edges=-1;
		try{
			BufferedReader br1=new BufferedReader(new FileReader(path1));
			while((line1=br1.readLine()) !=null){
				no_nodes=no_nodes+1;
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		try{
			BufferedReader br2=new BufferedReader(new FileReader(path2));
			while((line2=br2.readLine()) !=null){
				no_edges=no_edges+1;
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		float value=(float)(2*no_edges)/(float)no_nodes;
		System.out.printf("%.2f", value);
		System.out.println();
	}
	public boolean compare(Node x,Node y){
		if (x.data<y.data){
			return true;
		}
		else if(x.data>y.data){
			return false;
		}
		else{
			if (x.name.compareTo(y.name)<0){
				return true;
			}
			else{
				return false;
			}
		}
	}
	public void merge(Node List[], int l, int m, int r){
		int a1=m-l+1;
		int a2=r-m;
		Node L[]=new Node[a1];
		Node R[]=new Node[a2];
		for (int u=0;u<a1;++u){
			L[u]=List[l+u];
		}
		for (int v=0;v<a2;++v){
			R[v]=List[m+1+v];
		}
		int w=l;
		int v=0;
		int u=0;
		while (u<a1 && v<a2){
			if (compare(L[u],R[v])==false){
				List[w]=L[u];
				u++;
				w++;
			}
			else{
				List[w]=R[v];
				v++;
				w++;
			}
		}
		while (u<a1){
			List[w]=L[u];
			u++;
			w++;
		}
		while (v<a2){
			List[w]=R[v];
			v++;
			w++;
		}
	}
	public void sort(Node List[],int l,int r){
		if (l<r){
			int m=(l+r)/2;
			sort(List,l,m);
			sort(List,m+1,r);
			merge(List,l,m,r);
		}
	}
	public void rank(String path1, String path2){
		int count=-1;
		String line="";
		HashMap<String, Integer> map = new HashMap<>();
		Vector<Node> name_value=new Vector<Node>();
		try{
			BufferedReader mapper=new BufferedReader(new FileReader(path1));
			while((line=mapper.readLine())!=null){
				String[] values=line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				if (count!=-1){
					if (values[1].charAt(0)=='"'){
						int z=values[1].length();
						values[1]=values[1].substring(1,z-1);
					}
					map.put(values[1],count);
					Node node=new Node(0,values[1]);
					name_value.add(node);
					count=count+1;
				}
				else{
					count=count+1;
				}
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		String line2=""; 
		int count1=-1;
		try{
			BufferedReader valuer=new BufferedReader(new FileReader(path2));
			while((line2=valuer.readLine())!=null){
				String[] values1=line2.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
				if (count1!=-1){
					int j=Integer.parseInt(values1[2]);
					if (values1[1].charAt(0)=='"'){
						int z1=values1[1].length();
						values1[1]=values1[1].substring(1,z1-1);
					}
					if (values1[0].charAt(0)=='"'){
						int z2=values1[0].length();
						values1[0]=values1[0].substring(1,z2-1);
					}
					name_value.get(map.get(values1[0])).data=name_value.get(map.get(values1[0])).data+j;
					name_value.get(map.get(values1[1])).data=name_value.get(map.get(values1[1])).data+j;
					count1=count1+1;
				}
				else{
					count1=count1+1;
				}
			}
		} catch(FileNotFoundException e){
			e.printStackTrace();
		} catch(IOException e){
			e.printStackTrace();
		}
		Node List[]=new Node[count];
		for (int k=0;k<count;k++){
			List[k]=name_value.get(k);
		}
		sort(List,0,count-1);
		String List1[]=new String[count];
		for (int k1=0;k1<count;k1++){
			List1[k1]=List[k1].name;
		}
		String output=String.join(",",List1);
		System.out.println(output);
	}
	public static void main(String[] args) throws IOException {
		if (args.length==3){
			String path1=args[0];
			String path2=args[1];
			String function=args[2];
			A4_2019CS10413 as=new A4_2019CS10413();
			if (args[2].equals("average")){
				as.average(path1,path2);
			}
			else if(args[2].equals("rank")){
				as.rank(path1,path2);
			}
		}
	}
} 
class Node{
	public int data;
	public String name;
	public Node(int data,String name){
		this.data=data;
		this.name=name;
	}
}