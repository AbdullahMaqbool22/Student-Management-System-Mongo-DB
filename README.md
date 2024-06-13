# Student Portal Management System

This project is a comprehensive Student Portal Management System built using Java and MongoDB. It provides functionality for managing students, courses, enrollments, fees, grades, attendance, teachers, and fines. The project includes CRUD operations, aggregation pipeline queries, and joins to handle complex data retrieval scenarios.

## Features

- **CRUD Operations:** Create, Read, Update, Delete operations for all entities.
- **Aggregation Pipeline:** MongoDB aggregation pipeline queries to perform complex data analysis.
- **Join Operations:** Data retrieval across multiple collections using lookup.

## Technologies Used

- Java
- MongoDB
- Maven
- SLF4J for logging

## Setup

### Prerequisites

- Java 17
- MongoDB
- Maven

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/your-username/student-portal-management-system.git
   cd student-portal-management-system
   ```

2. Install Maven dependencies:
   ```sh
   mvn clean install
   ```

3. Ensure MongoDB is running on `localhost:27017`.

## Configuration

Ensure the MongoDB connection string and database name are correctly configured in the `DatabaseConnection` class:

```java
public class DatabaseConnection {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DatabaseConnection(String connectionString, String dbName) {
        mongoClient = MongoClients.create(connectionString);
        database = mongoClient.getDatabase(dbName);
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        mongoClient.close();
    }
}
```

## Usage

Run the `StudentPortalManagementSystem` main class to start the CLI-based system:

```sh
mvn exec:java -Dexec.mainClass="com.example.StudentPortalManagementSystem"
```

Follow the prompts in the command line interface to perform various operations.

## Aggregation Pipeline Queries

Below are some example aggregation pipeline queries you can run in MongoDB shell to analyze your data:

### 1. Total Fees Paid by a Student

```javascript
var studentId = ObjectId("60b8d295f1f4e87d2e6d9b69");  // Replace with actual studentId

db.fees.aggregate([
    { $match: { studentId: studentId } },
    { $group: {
        _id: "$studentId",
        totalFees: { $sum: "$amount" }
    }},
    { $lookup: {
        from: "students",
        localField: "_id",
        foreignField: "_id",
        as: "studentDetails"
    }},
    { $unwind: "$studentDetails" },
    { $project: {
        _id: 0,
        studentId: "$_id",
        studentName: "$studentDetails.name",
        totalFees: 1
    }}
])
```

### 2. Average Grade for a Course

```javascript
var courseId = ObjectId("60b8d295f1f4e87d2e6d9b70");  // Replace with actual courseId

db.grades.aggregate([
    { $match: { courseId: courseId } },
    { $group: {
        _id: "$courseId",
        averageGrade: { $avg: "$grade" }  // Assuming grades are numeric
    }},
    { $lookup: {
        from: "courses",
        localField: "_id",
        foreignField: "_id",
        as: "courseDetails"
    }},
    { $unwind: "$courseDetails" },
    { $project: {
        _id: 0,
        courseId: "$_id",
        courseName: "$courseDetails.name",
        averageGrade: 1
    }}
])
```

### 3. Attendance Percentage for a Student in a Course

```javascript
var studentId = ObjectId("60b8d295f1f4e87d2e6d9b69");  // Replace with actual studentId
var courseId = ObjectId("60b8d295f1f4e87d2e6d9b70");  // Replace with actual courseId

db.attendance.aggregate([
    { $match: { studentId: studentId, courseId: courseId } },
    { $group: {
        _id: "$studentId",
        totalClasses: { $sum: 1 },
        attendedClasses: { $sum: { $cond: [ "$present", 1, 0 ] } }
    }},
    { $project: {
        _id: 0,
        studentId: "$_id",
        totalClasses: 1,
        attendedClasses: 1,
        attendancePercentage: { $multiply: [ { $divide: [ "$attendedClasses", "$totalClasses" ] }, 100 ] }
    }}
])
```

### 4. Courses Taught by a Teacher

```javascript
var teacherId = ObjectId("60b8d295f1f4e87d2e6d9b71");  // Replace with actual teacherId

db.teachers.aggregate([
    { $match: { _id: teacherId } },
    { $lookup: {
        from: "courses",
        localField: "_id",
        foreignField: "teacherId",
        as: "courses"
    }},
    { $unwind: "$courses" },
    { $project: {
        _id: 0,
        teacherId: "$_id",
        teacherName: "$name",
        courseId: "$courses._id",
        courseName: "$courses.name"
    }}
])
```

### 5. Students Enrolled in a Course

```javascript
var courseId = ObjectId("60b8d295f1f4e87d2e6d9b70");  // Replace with actual courseId

db.courses.aggregate([
    { $match: { _id: courseId } },
    { $lookup: {
        from: "enrollments",
        localField: "_id",
        foreignField: "courseId",
        as: "enrollments"
    }},
    { $unwind: "$enrollments" },
    { $lookup: {
        from: "students",
        localField: "enrollments.studentId",
        foreignField: "_id",
        as: "studentDetails"
    }},
    { $unwind: "$studentDetails" },
    { $project: {
        _id: 0,
        courseId: "$_id",
        courseName: "$name",
        studentId: "$studentDetails._id",
        studentName: "$studentDetails.name",
        studentEmail: "$studentDetails.email"
    }}
])
```

### 6. Total Fines for a Student

```javascript
var studentId = ObjectId("60b8d295f1f4e87d2e6d9b69");  // Replace with actual studentId

db.fines.aggregate([
    { $match: { studentId: studentId } },
    { $group: {
        _id: "$studentId",
        totalFines: { $sum: "$amount" }
    }},
    { $lookup: {
        from: "students",
        localField: "_id",
        foreignField: "_id",
        as: "studentDetails"
    }},
    { $unwind: "$studentDetails" },
    { $project: {
        _id: 0,
        studentId: "$_id",
        studentName: "$studentDetails.name",
        totalFines: 1
    }}
])
```

### 7. Grades of Students in a Course

```javascript
var courseId = ObjectId("60b8d295f1f4e87d2e6d9b70");  // Replace with actual courseId

db.courses.aggregate([
    { $match: { _id: courseId } },
    { $lookup: {
        from: "grades",
        localField: "_id",
        foreignField: "courseId",
        as: "grades"
    }},
    { $unwind: "$grades" },
    { $lookup: {
        from: "students",
        localField: "grades.studentId",
        foreignField: "_id",
        as: "studentDetails"
    }},
    { $unwind: "$studentDetails" },
    { $project: {
        _id: 0,
        courseId: "$_id",
        courseName: "$name",
        studentId: "$studentDetails._id",
        studentName: "$studentDetails.name",
        grade: "$grades.grade"
    }}
])
```

## Contributing

Contributions are welcome! Please open an issue or submit a pull request for any changes or improvements.

## License

This project is licensed under the EPL 2.0 License.
