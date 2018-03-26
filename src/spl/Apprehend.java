package spl;

public class Apprehend {
	
	int[][] arr=new int[37][3];
	int[][] path=new int[37][8];
	
	int[][] Altarr=new int[37][3];
	
	public int source,destination,previous;
	
	public int counter=0;
	
	
	
	public Apprehend(int[][] arr,int[][] path) {
		
		this.arr=arr;
		this.path=path;
		
	}
	
	
	public boolean toCatch() {
		
	
				BestPawn bestpawn=new BestPawn(arr,path);
				
				int s=1,temp=0,tempX1=0,tempX2=0,tempX3=0;
				
				
				for(int i=0;i<37;i++) {
					
					
					
					int element=i;
					if(Altarr[element][2]==1) {
					
						for(int j=0;j<8;j++) {
						
							for(int m=0;m<Altarr.length;m++) {
								
								for(int n=0;n<Altarr[0].length;n++) {
									
									Altarr[m][n]=arr[m][n];
									
									
								}
								
							}
						
						
							tempX1=element;
							
							previous=tempX1;
						
							temp=path[element][j];
							tempX2=temp;
						
						
							if(temp!=-1&&path[temp][j]!=-1) {
							
								tempX3=path[temp][j];
								if(path[element][j]!=-1&&Altarr[path[element][j]][2]==0&&Altarr[path[temp][j]][2]==0) {
									
									counter=0;
									if(findMyPawn(tempX3)) {
										
										if(!bestpawn.checkDangerTomove(source)&&Altarr[previous][2]!=1) {
											
											arr[source][2]=0;
											arr[destination][2]=2;
											
											if(!bestpawn.checkDangerPosition(destination)) {
										
													return true;
											}
										}
										
									}
									
									
								}
								
							}
							
						}	
						
					}
					
				}
				
			return false;
	}


	public boolean findMyPawn(int tempX3) {
		
		counter++;
		int tempX2=0;
		int tempX1=0;
		for(int j=0;j<8;j++) {
			
			
			
			tempX1=tempX3;
		
			
		
		
			if(tempX1!=-1&&path[tempX1][j]!=-1) {
			
				tempX2=path[tempX1][j];
				
				if(Altarr[tempX1][2]==0&&Altarr[tempX2][2]==2) {
					
					source=tempX2;
					destination=tempX1;
					
					return true;
				}
				else {
					
					previous=tempX1;
				}
			
					
					
			}
		}
		
		if(counter<25) findMyPawn(tempX2);
		
		return false;
	}

}
