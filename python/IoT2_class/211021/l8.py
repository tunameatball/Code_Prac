# 파이썬은 클래스 필드, 객체 필드를 지원한다
# 클래스를 통해 만들어진 객체를 인스턴스라 한다
# 클래스 명, 메소드 명 모두 한글이 가능하다.

class 사람:
    def __init__(self, 이름, 나이, 취미):
        self.이름 = 이름
        self.나이 = 나이
        self.취미 = 취미
        pass
    
    def 소개하다(self):
        print("이름은 {}이고 나이는 {}이며 취미는 {}입니다".format(self.이름, self.나이, self.취미))

class 책:
    def __init__(self, 도서명, 페이지, 분류):
        self.도서명 = 도서명
        self.페이지 = 페이지
        self.분류 = 분류
    
    def 정보(self):
        print("도서명 : {}, 페이지수 : {}, 분류 : {}".format(self.도서명, self.페이지, self.분류))
        



홍길동 = 사람("홍길동", 24, "없음")
홍길동.소개하다()

사람들 = [
    사람("철수", 22, "요리"),
    사람("영희", 21, "유튜브"),
    사람("바둑이", 5, "산책")
]

for 사람 in 사람들:
    사람.소개하다()

책들 = [
    책("IoT", 200, "IT"),
    책("영어", 350, "영어"),
    책("리눅스책", 460, "IT")
]

for 책 in 책들:
    책.정보()