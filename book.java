class book
{
string t,a;
float p;
void getdata(string s,string s1,float d)
{
t=s;
a=s1;
p=d;
}
void display()
{
system.out.println("title",+t);
system.out.println("author"+a);
system.out.println("price"+p);
}
public static void main(string args[])
{
book b=new book();
b.getdata("tejal","ashish",2000);
b.display();
}
}


 











