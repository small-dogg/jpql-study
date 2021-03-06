package jpql;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    tx.begin();
    try {
      // # 기본 문법과 쿼리 API
//      Team team = new Team();
//      team.setName("teamA");
//      em.persist(team);
////      for(int i=0;i<100;i++) {
//
//      Member member1 = new Member();
//      member1.setUsername("관리자");
//      member1.setAge(10);
//      member1.setTeam(team);
//      member1.setMemberType(MemberType.ADMIN);
//
//      Member member2 = new Member();
//      member2.setUsername("관리자");
//      member2.setAge(10);
//      member2.setTeam(team);
//      member2.setMemberType(MemberType.ADMIN);
//
//      em.persist(member2);
////      }
//      em.flush();
//      em.clear();

      //반환타입이 명확 멤버이거나 문자열이거나
//      TypedQuery<Member> query1 = em.createQuery("select m from Member m",Member.class);
//      TypedQuery<String> query2 = em.createQuery("select m.username from Member m",String.class);
      //반환타입이 명확하지않아 문자열과 인트타입 둘다 있음..
//      Query query3 = em.createQuery("select m.username, m.age from Member m");

      //반환 값이 여러개일 때
//      List<Member> resultList = query1.getResultList();
      // -> 결과가 없으면 빈 리스트 반환
      //반환 값이 하나일 때
//      Member singleResult = query1.getSingleResult();
      // -> 결과가 없으면 NoResultException
      // -> 두개 이상이면 NonUniqueResultException
      // => Spring Data JPA를 사용하면, 결과가 없으면 버전에 따라, Optional과 null로 반환함

      //파라미터 바인딩 :파라미터
      // ?1로 쓰고, Parameter를 1로 하여, 포지션 기반으로 작성할 수도 있음(사용하지마세요..)
//      Member singleResult1 = em.createQuery("select m from Member m where m.username = :username",Member.class)
      //위 qlString에서 ':'를 작성하여 파라미터를 전달할 수 있음
//          .setParameter("username", "member1")
//          .getSingleResult();

      // # 프로젝션(SELECT)
      //엔티티 프로젝션
//      List<Member> result1 = em.createQuery("select m from Member m", Member.class)
//          .getResultList();

      //result2 쿼리형태보다는 result3과 같이 join을 하여 사용하는 것이 나음
//      List<Team> result2 = em.createQuery("select m.team from Member m", Team.class)
//          .getResultList();
//      List<Team> result3 = em.createQuery("select t from Member m join m.team t", Team.class)
//          .getResultList();

      //임베디드타입 프로젝션
//      List<Address> result4 = em.createQuery("select o.address from Order o", Address.class)
//          .getResultList();

      //스칼라 타입 프로젝션
//      List resultList1 = em.createQuery("select distinct m.username, m.age from Member m").getResultList();
//
//      for (Object o : resultList1) {
//        Object[] result = (Object[]) o;
//        for (Object o1 : result) {
//          System.out.println(o1);
//        }
//      }

      //제네릭에 직접 오브젝트 배열 작성
//      List<Object[]> resultList2 = em.createQuery("select distinct m.username, m.age from Member m").getResultList();

      //new 연산자로
//      List<MemberDto> resultList3 = em.createQuery(
//              "select new jpql.MemberDto(m.username, m.age) from Member m", MemberDto.class)
//          .getResultList();

      //#페이징
//      List<Member> resultList = em.createQuery("select m from Member m order by m.age desc",
//              Member.class)
//          .setFirstResult(1)
//          .setMaxResults(10)
//          .getResultList();
//
//      System.out.println("resultList.size() = " + resultList.size());
//      for (Member member1 : resultList) {
//        System.out.println("member = " + member1);
//      }

      //#조인
      //Inner Join 내부조인
      em.createQuery("select m from Member m inner join m.team t").getResultList();

      //Outer Join 외부조인
      em.createQuery("select m from Member m left outer join m.team t").getResultList();

      //세타 조인
      em.createQuery("select count(m) from Member m, Team t where m.username = t.name")
          .getResultList();

      //조인 - ON 절
      //- 조인 대상 필터링
      // ex) 회원과 팀을 조인하면서, 팀 이름이 A인 팀만 조인
      em.createQuery("select m, t from Member m LEFT JOIN m.team t on t.name = 'A'");

      //- 연관관계 없는 엔티티 외부 조인
      // ex) 회원의 이름과 팀의 이름이 같은 대상 외부 조인
      em.createQuery("select m, t from Member m left join Team t on m.username= t.name");

      //# 서브 쿼리

      //JPQL 타입 표현과 기타식
//      String query = "select m.username, 'HELLO', TRUE From Member m "
//          + "where m.memberType = :userType";
//
//      List<Object[]> resultList = em.createQuery(query)
//          .setParameter("userType", MemberType.ADMIN)
//          .getResultList();

//      for (Object[] objects : resultList) {
//        System.out.println("objects[0] = " + objects[0]);
//        System.out.println("objects[1] = " + objects[1]);
//        System.out.println("objects[2] = " + objects[2]);
//      }

      //# 조건식

//      String query = "select "
//          + "case when m.age <= 10 then '학생요금'"
//          + "when m.age >= 60 then '경로요금'"
//          + "else '일반요금' end"
//          + " from Member m";
//      List<String> resultList = em.createQuery(query, String.class)
//          .getResultList();
//      for (String s : resultList) {
//        System.out.println("s = " + s);
//      }

      //coalesce
//      String query = "select coalesce(m.username, '이름 없는 회원') as username from Member m";
//      em.createQuery(query);
//      List<String> resultList = em.createQuery(query, String.class)
//          .getResultList();
//      for (String s : resultList) {
//        System.out.println("s = " + s);
//      }

      //nullif
//      String query = "select nullif(m.username, '관리자') as username from Member m";
//      em.createQuery(query);
//      List<String> resultList = em.createQuery(query, String.class)
//          .getResultList();
//      for (String s : resultList) {
//        System.out.println("s = " + s);
//      }

      //#JPQL 함수
//      String query = "select 'a' || 'b' From Member m";
//      String query = "select concat('a', 'b') From Member m";
//      String query = "select substring(m.username, 2, 3) From Member m";
//      String query = "select locate('de','abcdefg') From Member m"; // => return (Integer)4
//      String query = "select size(t.members) From Team t"; // => 컬렉션의 크기를 출력해줌, 왠만하면 안씀..

      //사용자 정의 함수 호출
//      String query = "select function('group_concat',m.username) From Member m";
//      String query = "select group_concat(m.username) From Member m";
//      em.createQuery(query);
//      List<String> resultList = em.createQuery(query, String.class)
//          .getResultList();
//      for (String s : resultList) {
//        System.out.println("s = " + s);
//      }

      //# 경로 표현식
      //상태 필드, 단일 값 연관 경로, 컬렉션 값 연관 경로 -> 묵시적 내부 조인이 발생하기 때문에 명시적 조인을 사용해야함.

      //# ★페치 조인★
      //대부분의 N+1 문제를 해결할 수 있음
      //한방 쿼리
      Team teamA = new Team();
      teamA.setName("teamA");
      em.persist(teamA);

      Team teamB = new Team();
      teamB.setName("teamB");
      em.persist(teamB);

      Member member1 = new Member();
      member1.setUsername("회원A");
      member1.setTeam(teamA);
      em.persist(member1);
      Member member2 = new Member();
      member2.setUsername("회원B");
      member2.setTeam(teamA);
      em.persist(member2);
      Member member3 = new Member();
      member3.setUsername("회원C");
      member3.setTeam(teamB);
      em.persist(member3);

      em.flush();
      em.clear();

//      String query = "select m from Member m";(1)
//      String query = "select m from Member m join fetch m.team";
//      List<Member> resultList = em.createQuery(query).getResultList();
//      
//      for (Member member : resultList) {
//        System.out.println("member.getUsername() = " + member.getUsername() + "," + member.getTeam().getName());
      //회원1, 팀A(SQL) (1)
      //회원2, 팀A(1차캐시) (1)
      //회원3, 팀B(SQL) (1)

      //회원 100명을 조회해서 모든 회원이 다 서로다른 팀이라면 -> 100 + 1 즉, N+1 문제임. (1)
      // -> Fetch join || Batch Size

      //LAZY 로딩으로 세팅하더라도, fetch join이 우선순위가 높음.

      //일대다관계, 컬렉션 페치 조인
//      }

      //SQL결과는 3개지만 애플리케이션 레벨에서 중복제거를 추가 수행함.
//      String query = "select distinct t From Team t join fetch t.members";
//      List<Team> resultList = em.createQuery(query).getResultList();
//      for (Team team : resultList) {
//        System.out.println("team.getName() = " + team.getName() + "| members = " + team.getMembers().size());
      //일대다 조인은 데이터가 뻥튀기 될 수 있음

//      }

      String query = "select t from Team t";
      List<Team> resultList = em.createQuery(query, Team.class).getResultList();

      System.out.println("resultList.size() = " + resultList.size());

      //이같은 경우 멤버를 조회할때 새로운 멤버에 대해 조회가 추가적으로 발생하는데,
      //persistence.xml에 batch fetch size를 작성하여, 한번에 필요한 대상을 조회할 수 있음.
      for (Team team : resultList) {
        System.out.println("team = " + team + " | members = " + team.getMembers().size());
        for (Member member : team.getMembers()) {
          System.out.println("-> member = " + member);
        }
      }

      // 실무에서 발생하는 최적화 이슈는 대부분 N+1 문제이고 fetch join으로 대부분 해결한다고 함.

      //# 엔티티 직접 사용

      //아래의 두 쿼리는 동일하다. 엔티티를 쿼리상 작성하게 되면 엔티의 기본키 값이 넘어간다.
      String sql1 = "select count(m.id) from Member m";
      String sql2 = "select count(m) from Member m";

//      String query2 = "select m from Member m where m = :member";
      String query3 = "select m from Member m where m.id = :memberId";

      Member member = em.createQuery(query3, Member.class)
//          .setParameter("member", member1)
          .setParameter("memberId", member1.getId())
          .getSingleResult();

      //외래키도 마찬가지.
      String query4 = "select m from Member m where m.team = :team";
      List<Member> members = em.createQuery(query4, Member.class)
//          .setParameter("member", member1)
          .setParameter("team", teamA)
          .getResultList();

      for (Member member4 : members) {
        System.out.println("member4 = " + member4);
      }


      //#Named 쿼리
      //애플리케이션 로딩시점에 쿼리의 문법 오류를 잡아줄 수 있음

      List<Member> resultList1 = em.createNamedQuery("Member.findByUsername", Member.class)
          .setParameter("username", "회원A").getResultList();

      for (Member member4 : resultList1) {
        System.out.println("member4 = " + member4);
      }

      //#벌크 연산
      //update나 delete연산을 여러번수행해야할 때.
      //하이버네이트 사용시 insert도 사용 가능.
      // 영속성 컨텍스트를 무시하고 수행함
      //  - 벌크 연산을 먼저 수행
      // or
      //  - 벌크 연산 수행 후 영속성 컨텍스트 초기화
      int resultCount = em.createQuery("update Member m set m.age=20").executeUpdate();

      System.out.println("resultCount = " + resultCount);

      //영속성 컨텍스트에 반영이 되지 않아서 값이 0으로 출력됨(데이터 정합성이 깨짐.)
      System.out.println("member1 = " + member1.getAge());
      System.out.println("member2 = " + member2.getAge());
      System.out.println("member3 = " + member3.getAge());

      //영속성 컨텍스트 초기화
      em.clear();

      Member member4 = em.find(Member.class, member1.getId());
      System.out.println("member4 = " + member4);

      tx.commit();
    } catch (Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }
    emf.close();
  }

  private static void logic(Member m1, Member m2) {
//    System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));
    System.out.println("m1 == m2 : " + (m1 instanceof Member));
    System.out.println("m1 == m2 : " + (m2 instanceof Member));

  }
}
