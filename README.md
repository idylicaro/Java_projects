# Java_projects

Alguns projetos em Java que desenvolvi para projetos da faculdade ou apenas estudar!
- Projeto Exception
- Projeto Minimização de AFD

# Agendas Contatos

Foi um pequeno projeto de um curso de Java Swing, que me ensinou um pouco de como aplicações mvc java funcionavam.

# Projeto Exception

Foi um enorme desafio avaliativo proposto pela matéria de Teoria da computação para converter expressão regular em autômato finito não-determinístico (AFND) via arquivo .jff (a extensão .jff é do jflap que é uma ferramenta muito usada na área feita pelo Prof.Susan H. Rodger da Duke University, Durham). 

# Projeto Minimização de AFD

Foi outro projeto proposto pela matéria de Teoria da computação com objetivo de criar uma ferramenta para minimizar um autômato finito determinístico (AFD) via arquivo .jff assim gerando outro AFD com a extensão .jff.

###### Equipe:
 - IDYL ICARO DOS SANTOS (@idylicaro)
 - CARLOS VICTOR ANDRADE DE JESUS ()
 - ALESSANDRO MESSIAS DE SOUZA ()
  
###### Escopo:
  Definimos autômato como um modelo matemático de máquinas, com entradas e saídas discretas, que reconhece um conjunto de palavras sobre um dado alfabeto. Um autômato pode ser finito ou infinito e determinístico ou não determinístico. Neste manual, iremos retratar sobre o autômato finito determinístico, pois é somente através dele que a ferramenta criada irá funcionar e fazer a minimização do AFD.
  Um autômato finito determinístico é uma Máquina de estados finita que aceita ou rejeita cadeias de símbolos gerando um único ramo de computação para cada cadeia de entrada. O autômato é dito finito porque o conjunto de estados possíveis (Q) é finito; e o mesmo é dito determinístico quando dado o estado atual de M, ao ler um determinado símbolo na finita de entrada existe apenas um próximo estado possível.
  A esta ferramenta, demos o nome de Minimizador de Autômatos Finitos Determinísticos. O processo de minimização de AFD consiste na transformação de um dado autômato finito determinístico (AFD) em outro equivalente que tenha um número mínimo de estados. 
    O objetivo principal da minimização de autômatos é tornar um autômato o menor possível, com o menor número de estados possíveis, ou seja, uma vez que o autômato não contém estados inúteis, e/ou inatingíveis e estados distintos equivalentes, o mesmo se torna irredutível, o que facilita muito sua compreensão e aplicabilidade
    Existe algumas condições para que o autômato finito(AF) seja minimizado, dentre elas o AF tem que ser determinístico, não pode ter estados inacessíveis, ou seja, os estados devem ser alcançáveis a partir do ponto inicial e deve ser completo. Para obter um AFD mínimo, deve-se eliminar estados não alcançáveis a partir do estado inicial e substituir cada grupo de estados equivalentes por um único estado. 
    Tendo em vista que temos as informações suficientes sobre AF, AFD e a ferramenta desenvolvida, minimização de AFD, daremos sequência para o passo a passo, onde irá mostrar a maneira como usar o programa.

 ###### Recomendação:
 - Framework usado para UI: javaFx,
 - Java :jdk-10.0.2
 - Cuidado com os padroes de nomes para arquivo(win pode recusar).
  
