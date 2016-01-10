class employee
{
string n;
float s;
void getdata(string s1,float w)
{
n=s1;
s=w;
}
void putdata()
{
system.out.println("name"+n);
system.out.println("salary"+s);
}
public static void main(string args[])
{
employee e=new employee();
e.getdata("tejal",3000);
e.putdata();
}
}
