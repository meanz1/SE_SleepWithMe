const express = require('express');
const mysql = require('mysql');

const app = express();
const port = 3000;

const con = mysql.createConnection({
    host: 'localhost',
    user: 'root',
    password: '960229',
    database: 'coco_db',
    dateStrings: 'date'
})
con.connect(function(err) {
    if(err) throw err;
    console.log('DB connected');
})

////////////////////////////////////////////////////////////////////////////////////////////////////

// 빈 문자열 확인 함수
function isEmpty(str) {
    if(typeof str == "undefined" || str == null || str == "") return true;
    return false ;
}

// 빈 배열 확인 함수
function isEmptyArr(arr)  {
    if(Array.isArray(arr) && arr.length === 0) return true;
    return false;
  }

////////////////////////////////////////////////////////////////////////////////////////////////////

// 계정 생성: id, pw -> user 추가, default profile 추가
app.post('/signup', (req, res) => {
    var id = req.query.id; var pw = req.query.pw;

    // user 추가 (이미 존재하는 id 인지 확인 필요)
    con.query(`INSERT INTO user (id, pw) VALUES (\'${id}\', \'${pw}\')`, (err) => {
        if(err) res.send('-1');
        else {
            // default profile 추가
            con.query(`INSERT INTO profile (user_id, name, gender, age, category1, category2) VALUES (\'${id}\', '김한양', '남', 30, '자연', '일상');`, (err) => {
                if(err) res.send('[/signup] fail');
                else res.send('[/signup] success');
            });
        }
    });
});

// 로그인: id, pw -> default profile (1번 프로필)
app.post('/login', (req, res) => {
    // console.log("lgd");
    var id = req.query.id; var pw = req.query.pw;
    console.log("ID: " + id + " / PW: " + pw);

    // login
    con.query(`SELECT user_idx FROM user WHERE id=\'${id}\' AND pw=\'${pw}\'`, (err, row) => {
        if(err) res.send('-1');
        else if(isEmptyArr(row)) res.send('-1');
        else if(isEmpty(row[0].user_idx)) res.send('-1');
        else {
            // default profile
            con.query(`SELECT * FROM profile WHERE user_id=\'${id}\'`, (err, rows) => {
                if(err) res.send('-1');
                else if(isEmptyArr(rows)) res.send('-1');
                else if(isEmpty(rows[0].profile_idx)) res.send('-1');
                else {
                    var result = rows.length + " ";
                    for(var i=0; i<rows.length; i++){
                        result = result + rows[i].profile_idx + " " + rows[i].user_id + " " + rows[i].name + " " + rows[i].gender + " " + rows[i].age + " " + rows[i].category1 + " " + rows[i].category2 + " ";
                    }
                    console.log(result);
                    console.log("login success");
                    res.send(result);
                }
            })
        }
    });
});

// 프로필 추가: id, name, gender, age, category1, category2 -> profile 추가
app.post('/profile/add', (req, res) => {
    var id = req.query.id;
    var name = req.query.name; var gender = req.query.gender; var age = req.query.age;
    var category1 = req.query.category1; var category2 = req.query.category2;

    // profile 추가
    con.query(`INSERT INTO profile (user_id, name, gender, age, category1, category2) VALUES (\'${id}\', \'${name}\', \'${gender}\', ${age}, \'${category1}\', \'${category2}\');`, (err) => {
        if(err) res.send('[/profile/add] fail');
        else res.send('[/profile/add] success');
    });
});

// 프로필 수정: profile_idx, name, gender, age, category1, category2 -> profile 수정
app.post('/profile/edit', (req, res) => {
    var profile_idx = req.query.profile_idx;
    var name = req.query.name; var gender = req.query.gender; var age = req.query.age;
    var category1 = req.query.category1; var category2 = req.query.category2;
    
    // profile 수정
    con.query(`UPDATE profile SET name=\'${name}\', gender=\'${gender}\', age=${age}, category1=\'${category1}\', category2=\'${category2}\' WHERE profile_idx=${profile_idx};`, (err) => {
        if(err) res.send('[/profile/edit] fail');
        else {
            res.send('[/profile/edit] success');
            // console.log("adfs");
        }
    });
});

// 프로필 불러오기: profile_idx -> profile (해당 프로필)
app.post('/profile/load', (req, res) => {
    var profile_idx = req.query.profile_idx;
    
    // profile
    con.query(`SELECT * FROM profile WHERE profile_idx=\'${profile_idx}\'`, (err, row) => {
        if(err) res.send('[/profile/load] fail');
        else if(isEmptyArr(row)) res.send('[/profile/load] fail');
        else if(isEmpty(row[0].profile_idx)) res.send('[/profile/load] fail');
        else {
            var result = row[0].profile_idx + " " + row[0].user_id + " " + row[0].name + " " + row[0].gender + " " + row[0].age + " " + row[0].category1 + " " + row[0].category2;
            res.send(result);
        }
    });
});

// 프로필 선택: id, name -> profile (해당 프로필)
app.post('/profile/select', (req, res) => {
    var id = req.query.id;
    var name = req.query.name;
    
    // profile
    con.query(`SELECT * FROM profile WHERE user_id=\'${id}\' AND name=\'${name}\'`, (err, row) => {
        if(err) res.send('[/profile/select] fail');
        else if(isEmptyArr(row)) res.send('[/profile/select] fail');
        else if(isEmpty(row[0].profile_idx)) res.send('[/profile/select] fail');
        else {
            var result = row[0].profile_idx + " " + row[0].user_id + " " + row[0].name + " " + row[0].gender + " " + row[0].age + " " + row[0].category1 + " " + row[0].category2;
            res.send(result);
        }
    });
});

// 레코드 추가: id, name, date(날짜 형식은 yyyy.MM.dd 혹은 yyyy-MM-dd 혹은 yyyy/MM/dd), achieve -> record 추가
app.post('/record/add', (req, res) => {
    var id = req.query.id;
    var name = req.query.name; var date = req.query.date; var achieve = req.query.achieve;

    // record 추가
    con.query(`INSERT INTO record (user_id, name, date, achieve) VALUES (\'${id}\', \'${name}\', \'${date}\', ${achieve});`, (err) => {
        if(err) res.send('[/record/add] fail');
        else res.send('[/record/add] success');
    });
});

// 레코드 불러오기: id, name, year, month -> record (해당 월 전부 출력)
app.post('/record/load', (req, res) => {
    var id = req.query.id;
    var name = req.query.name;
    var year = req.query.year; var month = req.query.month;

    // profile
    con.query(`SELECT * FROM record WHERE user_id=\'${id}\' AND name=\'${name}\' AND date>='${year}-${month}-01' AND date<='${year}-${month}-31' ORDER BY date ASC`, (err, rows) => {
        if(err) res.send('[/record/load] fail');
        else {
            var result = rows.length + "\n";
            for(var i=0; i<rows.length; i++) {
                var dateFormat = rows[i].date.split(" ")[0].split("-");
                result += dateFormat[2] + " " + rows[i].achieve + " ";
            }
            res.send(result);
        }
    });
});

////////////////////////////////////////////////////////////////////////////////////////////////////

app.listen(port, () => {
    console.log(`Server is listening at 35.213.59.137:${port}`);
});