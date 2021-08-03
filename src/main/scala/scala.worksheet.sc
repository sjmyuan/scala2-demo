def add(x: Int)(implicit y: Int): Int = x + y

implicit val instance: Int = 10

add(1)
add(1)(5)

trait Show[A] {
 def show(x: A): String
}

def printListWithUsing[A](list: List[A])(implicit show: Show[A]): String = list.map(x => show.show(x)).mkString(";")
def printListWithContextBound[A:Show](list: List[A]): String = list.map(x => implicitly[Show[A]].show(x)).mkString(";")

implicit val intShow: Show[Int] = new Show[Int] {
  def show(x: Int): String = x.toString 
}

printListWithUsing(List(1, 2, 3, 4))
printListWithContextBound(List(1, 2, 3, 4))

implicit class ShowOps[A: Show](v: A) {
  def show(): String = implicitly[Show[A]].show(v)
}

implicit def listShow[A: Show]: Show[List[A]] = new Show[List[A]] {
  def show(v: List[A]): String = v.map(_.show()).mkString(";") 
}


1.show()
List(1, 2, 3).show()