import java.io.*;
import java.util.ArrayList;
import java.util.Vector;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Iterator;
import java.util.Map;
public class A4_2019CS10413{
	public boolean compare(ArrayList<String> X,ArrayList<String> Y){
		if (X.size()<Y.size()){
			return true;
		}
		else if (X.size()>Y.size()){
			return false;
		}
		else{
			if (X.get(0).compareTo(Y.get(0))<0){
				return true;
			}
			else{
				return false;
			}
		}
	}
	public void sort(ArrayList<ArrayList<String>> List,int l,int r){
		if (l<r){
			int m=(l+r)/2;
			sort(List,l,m);
			sort(List,m+1,r);
			merge(List,l,m,r);
		}
	}
	public void merge(ArrayList<ArrayList<String>> List,int l,int m,int r){
		int a1=m-l+1;
		int a2=r-m;
		ArrayList<ArrayList<String>> L=new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> R=new ArrayList<ArrayList<String>>();
		for (int u=0;u<a1;u++){
			L.add(List.get(u+l));
		}
		for (int v=0;v<a2;v++){
			R.add(List.get(m+1+v));
		}
		int w=l;
		int v=0;
		int u=0;
		while (u<a1 && v<a2){
			if (compare(L.get(u),R.get(v))==false){
				List.set(w,L.get(u));
				u++;
				w++;
			}
			else{
				List.set(w,R.get(v));
				v++;
				w++;
			}
		}
		while (u<a1){
			List.set(w,L.get(u));
			u++;
			w++;
		}
		while (v<a2){
			List.set(w,R.get(v));
			v++;
			w++;
		}
	}
	public void merge1(String[] List1,int l,int m,int r){
		int a1=m-l+1;
		int a2=r-m;
		String[] L=new String[a1];
		String[] R=new String[a2];
		for (int u=0;u<a1;u++){
			L[u]=List1[l+u];
		}
		for (int v=0;v<a2;++v){
			R[v]=List1[m+1+v];
		}
		int w=l;
		int v=0;
		int u=0;
		while (u<a1 && v<a2){
			if (compare1(L[u],R[v])==false){
				List1[w]=L[u];
				u++;
				w++;
			}
			else{
				List1[w]=R[v];
				v++;
				w++;
			}
		}
		while (u<a1){
			List1[w]=L[u];
			u++;
			w++;
		}
		while (v<a2){
			List1[w]=R[v];
			v++;
			w++;
		}
	}
	public boolean compare1(String x,String y){
		if (x.compareTo(y)<0){
			return true;
		}
		else{
			return false;
		}
	}
	public void sort1(String[] List1,int l,int r){
		if (l<r){
			int m=(l+r)/2;
			sort1(List1,l,m);
			sort1(List1,m+1,r);
			merge1(List1,l,m,r);
		}
	}
	public void independent_storylines_dfs(String path1, String path2){
		String line1="";
		int no_nodes=-1;
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
		int count=-1;
		String line="";
		HashMap<String, Integer> map = new HashMap<>();
		ArrayList<ArrayList<String>> graph=new ArrayList<ArrayList<String>>();
		for (int i=0;i<no_nodes;i++){
			ArrayList<String> z=new ArrayList<String>();
			graph.add(z);
		}
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
					graph.get(count).add(values[1]);
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
					//int j=Integer.parseInt(values1[2]);
					if (values1[1].charAt(0)=='"'){
						int z1=values1[1].length();
						values1[1]=values1[1].substring(1,z1-1);
					}
					if (values1[0].charAt(0)=='"'){
						int z2=values1[0].length();
						values1[0]=values1[0].substring(1,z2-1);
					}
					graph.get(map.get(values1[0])).add(values1[1]);
					graph.get(map.get(values1[1])).add(values1[0]);
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
		boolean[] data=new boolean[no_nodes];
		for (int i=0;i<no_nodes;i++){
			data[i]=false;
		}
		ArrayList<ArrayList<String>> temp=new ArrayList<ArrayList<String>>();
		for (int i=0; i<graph.size();i++){
			if (data[i]==false){
				ArrayList<String> t=new ArrayList<String>();
				dfs(i,graph,data,t,map);
				String[] t1=new String[t.size()];
				for (int j=0;j<t.size();j++){
					t1[j]=t.get(j);
				}
				sort1(t1,0,t.size()-1);
				for (int j=0;j<t.size();j++){
					t.set(j,t1[j]);
				}
				temp.add(t);
			}
		}
		sort(temp,0,temp.size()-1);
		for (int i=0;i<temp.size();i++){
			String output=String.join(",",temp.get(i));
			System.out.println(output);
		}
	}
	public void dfs(int start, ArrayList<ArrayList<String>> graph,boolean[] data, ArrayList<String> t,HashMap<String, Integer> map){
		data[start]=true;
		t.add(graph.get(start).get(0));
		for (int j=1;j<graph.get(start).size();j++){
			int destination=map.get(graph.get(start).get(j));
			if (data[destination]==false){
				dfs(destination,graph,data,t,map);
			}
		}
	}
	
	public static void main(String[] args) throws IOException{
		if (args.length==3){
			String path1=args[0];
			String path2=args[1];
			String function=args[2];
			A4_2019CS10413 as=new A4_2019CS10413();
			if (args[2].equals("independent_storylines_dfs")){
				as.independent_storylines_dfs(path1,path2);
			}
		}
	}
}